package com.green.gogiro.reservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.gogiro.MockMvcConfig;
import com.green.gogiro.common.ResVo;
import com.green.gogiro.reservation.model.*;
import com.green.gogiro.security.JwtTokenProvider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockMvcConfig
@WebMvcTest(ReservationController.class)
class ReservationControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private ReservationService service;
    @MockBean
    private JwtTokenProvider jwtTokenProvider;
    private static final int EXPECTED= 38;

    @Test
    @WithMockUser
    void postReservationTest() throws Exception{
        ResVo vo= new ResVo(EXPECTED);
        ReservationInsDto dto= new ReservationInsDto();
        dto.setIshop(10);
        dto.setDate("1234-12-31 10:00:00");
        dto.setHeadCount(10);
        given(service.postReservation(any())).willReturn(vo);
        MvcResult mr= mvc.perform(MockMvcRequestBuilders.post("/api/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        verify(service).postReservation(any());
        String content= mr.getResponse().getContentAsString();
        ResVo result= mapper.readValue(content, ResVo.class);
        assertEquals(EXPECTED,result.getResult());
    }
    @Test
    @WithMockUser
    void postPickupTest() throws Exception{
        ResVo vo= new ResVo(EXPECTED);
        PickupInsDto dto= new PickupInsDto();
        dto.setIbutcher(10);
        dto.setDate("1235-12-31 10:00:00");
        List<PickupMenuDto> menus= new ArrayList<>();
        PickupMenuDto menu= PickupMenuDto.builder()
                                         .ipickup(1)
                                         .ibutMenu(1)
                                         .count(1)
                                         .build();
        menus.add(menu);
        dto.setMenus(menus);
        given(service.postPickup(any())).willReturn(vo);
        MvcResult mr= mvc.perform(MockMvcRequestBuilders.post("/api/pickup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        verify(service).postPickup(any());
        String content= mr.getResponse().getContentAsString();
        ResVo result= mapper.readValue(content, ResVo.class);
        assertEquals(EXPECTED,result.getResult());
    }
    @Test
    @WithMockUser
    void cancelReservationTest() throws Exception{
        ResVo vo= new ResVo(EXPECTED);
        CancelDto dto= new CancelDto();
        dto.setCheckShop(0);
        dto.setIreser(1);
        given(service.cancelReservation(dto)).willReturn(vo);
        MvcResult mr= mvc.perform(MockMvcRequestBuilders.patch("/api/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String content= mr.getResponse().getContentAsString();
        ResVo result= mapper.readValue(content, ResVo.class);
        assertEquals(vo, result);
    }
    @Test
    @WithMockUser
    void putReservationTest() throws Exception{
        ResVo vo= new ResVo(EXPECTED);
        ReservationUpdDto dto= new ReservationUpdDto();
        dto.setIreser(12);
        dto.setDate("1934-12-12 10:00:00");
        dto.setHeadCount(3);
        given(service.putReservation(any())).willReturn(vo);
        MvcResult mr= mvc.perform(MockMvcRequestBuilders.put("/api/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        verify(service).putReservation(any());
        String content= mr.getResponse().getContentAsString();
        ResVo result= mapper.readValue(content, ResVo.class);
        assertEquals(EXPECTED,result.getResult());
    }

}