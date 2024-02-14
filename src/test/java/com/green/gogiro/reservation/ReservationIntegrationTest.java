package com.green.gogiro.reservation;

import com.green.gogiro.BaseIntegrationTest;
import com.green.gogiro.common.ResVo;
import com.green.gogiro.exception.RestApiException;
import com.green.gogiro.reservation.model.*;
import com.green.gogiro.user.model.UserSignVo;
import com.green.gogiro.user.model.UserSigninDto;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReservationIntegrationTest extends BaseIntegrationTest {
    private String accessToken() throws Exception {
        UserSigninDto dto = new UserSigninDto();
        dto.setEmail("dd11@naver.com");
        dto.setUpw("1212");
        String json = om.writeValueAsString(dto);
        MvcResult mr = mvc.perform(MockMvcRequestBuilders.post("/api/user/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = mr.getResponse().getContentAsString();
        UserSignVo result = om.readValue(content, UserSignVo.class);
        return result.getAccessToken();
    }
    @Test
    void postReservationTest() throws Exception{
        ReservationInsDto dto= new ReservationInsDto();
        dto.setIshop(10);
        dto.setDate("1234-12-31 10:00:00");
        dto.setHeadCount(10);
        try {
            MvcResult mr= mvc.perform(MockMvcRequestBuilders.post("/api/reservation")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(om.writeValueAsString(dto))
                            .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken()))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            String content= mr.getResponse().getContentAsString();
            ResVo result= om.readValue(content, ResVo.class);
            assertTrue(result.getResult()>0);
        } catch(RestApiException e) {
            e.printStackTrace();
            System.out.println("잘못된 파라미터입니다.");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("내부 서버 에러");
        }
    }
    @Test
    void postPickupTest() throws Exception{
        PickupInsDto dto= new PickupInsDto();
        dto.setIbutcher(1);
        dto.setDate("1235-12-31 10:00:00");
        List<PickupMenuDto> menus= new ArrayList<>();
        PickupMenuDto menu= PickupMenuDto.builder()
                .ipickup(1)
                .ibutMenu(1)
                .count(1)
                .build();
        menus.add(menu);
        dto.setMenus(menus);
        try {
            MvcResult mr= mvc.perform(MockMvcRequestBuilders.post("/api/pickup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(om.writeValueAsString(dto))
                            .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken()))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            String content= mr.getResponse().getContentAsString();
            ResVo result= om.readValue(content, ResVo.class);
            assertTrue(result.getResult()>0);
        } catch(RestApiException e) {
            e.printStackTrace();
            System.out.println("잘못된 파라미터입니다.");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("내부 서버 에러");
        }
    }
    @Test
    void cancelReservationTest() throws Exception {
        try {
            CancelDto dto= new CancelDto();
            dto.setCheckShop(0);
            dto.setIreser(1);

            MvcResult mr= mvc.perform(MockMvcRequestBuilders.patch("/api/reservation")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(om.writeValueAsString(dto))
                            .with(csrf()).header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken()))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();
            String content= mr.getResponse().getContentAsString();
            ResVo result= om.readValue(content, ResVo.class);
            assertEquals(1, result.getResult());
        } catch(Exception e) {
            System.out.println("없는 예약입니다");
        }

    }
    @Test
    void putReservationTest() throws Exception{

        ReservationUpdDto dto= new ReservationUpdDto();
        dto.setIreser(12);
        dto.setDate("1934-12-12 10:00:00");
        dto.setHeadCount(3);

        MvcResult mr= mvc.perform(MockMvcRequestBuilders.put("/api/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(dto))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content= mr.getResponse().getContentAsString();
        ResVo result= om.readValue(content, ResVo.class);
        assertEquals(1,result.getResult());
    }
}
