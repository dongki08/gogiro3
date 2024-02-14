package com.green.gogiro.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.green.gogiro.BaseIntegrationTest;
import com.green.gogiro.common.ResVo;
import com.green.gogiro.user.model.*;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.nio.charset.StandardCharsets;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserIntegrationTest extends BaseIntegrationTest {
    String accessToken() throws Exception {
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
    public void signupTest() throws Exception {
        UserSignupDto dto = new UserSignupDto();
        dto.setEmail("zxcasd@naver.com");
        dto.setUpw("1234");
        dto.setCheckUpw("1234");
        dto.setName("테스트");
        dto.setNickname("테스트");
        dto.setBirth("20110222");
        dto.setGender("남");
        dto.setAddress("대구어딘가");
        dto.setTel("01012345678");
        MockMultipartFile file = new MockMultipartFile("pic", "a.jpg", "multipart/form-data", "uploadFile".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile request = new MockMultipartFile("dto", null, "application/json", om.writeValueAsString(dto).getBytes(StandardCharsets.UTF_8));
        MvcResult mr = mvc.perform(MockMvcRequestBuilders.multipart(HttpMethod.POST, "/api/user/signup")
                        .file(file)
                        .file(request)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String content = mr.getResponse().getContentAsString();
        ResVo result = om.readValue(content, ResVo.class);
        assertTrue(result.getResult() > 0);
    }

    @Test
    public void signinTest() throws Exception {
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
        assertEquals(1, result.getResult());
        assertNotNull(result.getAccessToken());
    }

    @Test
    void signoutTest() throws Exception {
        MvcResult mr = mvc.perform(MockMvcRequestBuilders.post("/api/user/signout"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = mr.getResponse().getContentAsString();
        ResVo result = om.readValue(content, ResVo.class);
        assertEquals(1, result.getResult());
    }

    @Test
    void getRefreshToken() throws Exception {
        final UserSignVo vo = UserSignVo.builder().result(123).build();

        MvcResult mr = mvc.perform(MockMvcRequestBuilders.get("/api/user/refresh-token"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = mr.getResponse().getContentAsString();
        UserSignVo result = om.readValue(content, UserSignVo.class);
        assertEquals(0, result.getResult());
    }

    @Test
    void putUserTest() throws Exception {
        UserUpdDto dto = new UserUpdDto();
        dto.setIuser(5);
        dto.setNickname("테스트");
        dto.setAddress("대구어딘가");
        dto.setTel("01012345678");
        MockMultipartFile file = new MockMultipartFile("pic", "aaa.jpg", "multipart/form-data", "uploadFile".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile request = new MockMultipartFile("dto", null, "application/json", om.writeValueAsString(dto).getBytes(StandardCharsets.UTF_8));

        MvcResult mr = mvc.perform(MockMvcRequestBuilders.multipart(HttpMethod.PUT, "/api/user")
                        .file(file)
                        .file(request)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = mr.getResponse().getContentAsString();
        ResVo result = om.readValue(content, ResVo.class);
        assertEquals(1, result.getResult());
    }

    @Test
    void getUserInfoTest() throws Exception {
        MvcResult mr = mvc.perform(MockMvcRequestBuilders.get("/api/user")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = mr.getResponse().getContentAsString();
        UserInfoVo result = om.readValue(content, UserInfoVo.class);
        assertNotNull(result.getEmail());
    }

    @Test
    void getReservationTest() throws Exception {
        MvcResult mr = mvc.perform(MockMvcRequestBuilders.get("/api/user/reservation")
                        .with(csrf())
                        .param("page", "1").header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String content = mr.getResponse().getContentAsString();
        List<ReservationVo> result = om.readValue(content, new TypeReference<>() {
        });
        assertNotNull(result);
    }

    @Test
    void checkNickNameTest() throws Exception {


        try {
            String nickname = "test123123";
            MvcResult mr = mvc.perform(MockMvcRequestBuilders.post("/api/user/signup/" + nickname)
                            .param("nickname", nickname))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            String content = mr.getResponse().getContentAsString();
            ResVo result = om.readValue(content, ResVo.class);
            assertEquals(1, result.getResult());
        } catch (Exception e) {
            System.out.println("중복된 닉네임입니다");
        }
    }

    @Test
    void getUserReviewTest() throws Exception {
        MvcResult mr = mvc.perform(MockMvcRequestBuilders.get("/api/user/review")
                        .param("page", "1").header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = mr.getResponse().getContentAsString();
        List<ReviewVo> result = om.readValue(content, new TypeReference<>() {
        });
        assertNotNull(result);
    }

    @Test
    void getUserBookmark() throws Exception {
        MvcResult mr = mvc.perform(MockMvcRequestBuilders.get("/api/user/bookmark")
                        .param("page", "1").header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = mr.getResponse().getContentAsString();
        List<BookmarkShopVo> result = om.readValue(content, new TypeReference<>() {
        });
        assertNotNull(result);
    }

    @Test
    void delShopReview() throws Exception {
        ReviewDelDto dto = new ReviewDelDto();
        dto.setCheckShop(0);
        dto.setIreview(1);

        try {
            MvcResult mr = mvc.perform(MockMvcRequestBuilders.delete("/api/user/review")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(om.writeValueAsString(dto))
                            .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken()))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            String content = mr.getResponse().getContentAsString();
            ResVo result = om.readValue(content, ResVo.class);
            assertTrue(result.getResult()==0||result.getResult()==1);
        } catch (Exception e) {
            System.out.println("잘못된 요청입니다");
        }
    }
}
