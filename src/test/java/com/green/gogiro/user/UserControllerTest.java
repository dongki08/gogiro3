package com.green.gogiro.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.gogiro.MockMvcConfig;
import com.green.gogiro.common.ResVo;
import com.green.gogiro.security.JwtTokenProvider;
import com.green.gogiro.user.model.*;
import org.junit.jupiter.api.Test;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockMvcConfig
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private UserService service;
    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Test
    @WithMockUser
    public void signupTest() throws Exception{
        final int EXPECTED= 12;
        UserSignupDto dto= new UserSignupDto();
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
        MockMultipartFile request = new MockMultipartFile("dto", null, "application/json", mapper.writeValueAsString(dto).getBytes(StandardCharsets.UTF_8));
        given(service.signup(any())).willReturn(new ResVo(EXPECTED));
        MvcResult mr= mvc.perform(MockMvcRequestBuilders.multipart(HttpMethod.POST,"/api/user/signup")
                                                        .file(file)
                                                        .file(request)
                                                        .accept(MediaType.APPLICATION_JSON)
                                                        .contentType(MediaType.MULTIPART_FORM_DATA)
                                                        .with(csrf()))
                         .andExpect(status().isOk())
                         .andDo(print())
                         .andReturn();
        verify(service).signup(any());
        String content= mr.getResponse().getContentAsString();
        ResVo result= mapper.readValue(content,ResVo.class);
        assertEquals(EXPECTED, result.getResult());
    }
    @Test
    @WithMockUser
    void signinTest() throws Exception{
        final UserSignVo vo= UserSignVo.builder().result(23).build();
        given(service.signin(any(),any(),any())).willReturn(vo);
        UserSigninDto dto= new UserSigninDto();
        dto.setEmail("dd11@naver.com");
        dto.setUpw("1212");
        String json= mapper.writeValueAsString(dto);

        MvcResult mr= mvc.perform(MockMvcRequestBuilders.post("/api/user/signin")
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(json)
                                                        .with(csrf()))
                         .andExpect(status().isOk())
                         .andDo(print())
                         .andReturn();
        verify(service).signin(any(), any(),any());
        String content= mr.getResponse().getContentAsString();
        UserSignVo result= mapper.readValue(content, UserSignVo.class);
        assertEquals(vo.getResult(), result.getResult());
    }
    @Test
    @WithMockUser
    void signoutTest() throws Exception{
        final ResVo vo= new ResVo(45);
        given(service.signout(any())).willReturn(vo);


        MvcResult mr= mvc.perform(MockMvcRequestBuilders.post("/api/user/signout")
                                                        .with(csrf()))
                         .andExpect(status().isOk())
                         .andDo(print())
                         .andReturn();
        verify(service).signout(any());
        String content= mr.getResponse().getContentAsString();
        ResVo result= mapper.readValue(content, ResVo.class);
        assertEquals(vo.getResult(), result.getResult());
    }
    @Test
    @WithMockUser
    void getRefreshToken() throws Exception{
        final UserSignVo vo= UserSignVo.builder().result(123).build();
        given(service.getRefreshToken(any())).willReturn(vo);
        MvcResult mr= mvc.perform(MockMvcRequestBuilders.get("/api/user/refresh-token")
                                                        .with(csrf()))
                         .andExpect(status().isOk())
                         .andDo(print())
                         .andReturn();
        verify(service).getRefreshToken(any());
        String content= mr.getResponse().getContentAsString();
        ResVo result= mapper.readValue(content, ResVo.class);
        assertEquals(vo.getResult(), result.getResult());
    }
    @Test
    @WithMockUser
    void putUserTest() throws Exception{
        final int EXPECTED= 21;
        UserUpdDto dto= new UserUpdDto();
        dto.setNickname("테스트");
        dto.setAddress("대구어딘가");
        dto.setTel("01012345678");
        MockMultipartFile file = new MockMultipartFile("pic", "aaa.jpg", "multipart/form-data", "uploadFile".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile request = new MockMultipartFile("dto", null, "application/json", mapper.writeValueAsString(dto).getBytes(StandardCharsets.UTF_8));
        given(service.updateUser(any())).willReturn(new ResVo(EXPECTED));
        MvcResult mr= mvc.perform(MockMvcRequestBuilders.multipart(HttpMethod.PUT,"/api/user")
                                                        .file(file)
                                                        .file(request)
                                                        .accept(MediaType.APPLICATION_JSON)
                                                        .contentType(MediaType.MULTIPART_FORM_DATA)
                                                        .with(csrf()))
                         .andExpect(status().isOk())
                         .andDo(print())
                         .andReturn();
        verify(service).updateUser(any());
        String content= mr.getResponse().getContentAsString();
        ResVo result= mapper.readValue(content,ResVo.class);
        assertEquals(EXPECTED, result.getResult());
    }
    @Test
    @WithMockUser
    void getUserInfoTest() throws Exception{
        final UserInfoVo vo= new UserInfoVo();
        String name= "userInfoTest";
        vo.setName(name);
        given(service.selUserInfo()).willReturn(vo);
        MvcResult mr= mvc.perform(MockMvcRequestBuilders.get("/api/user")
                                                        .with(csrf()))
                         .andExpect(status().isOk())
                         .andDo(print())
                         .andReturn();
        verify(service).selUserInfo();
        String content= mr.getResponse().getContentAsString();
        UserInfoVo result= mapper.readValue(content, UserInfoVo.class);
        assertEquals(name, result.getName());
    }
    @Test
    @WithMockUser
    void getReservationTest() throws Exception {
        List<ReservationVo> list= new ArrayList<>();
        ReservationVo vo1= new ReservationVo();
        vo1.setIreser(100);
        list.add(vo1);
        ReservationVo vo2= new ReservationVo();
        vo2.setIreser(200);
        list.add(vo2);
        given(service.getReservation(any())).willReturn(list);
        MvcResult mr= mvc.perform(MockMvcRequestBuilders.get("/api/user/reservation")
                                                        .with(csrf())
                                                        .param("page","1"))
                         .andExpect(status().isOk())
                         .andDo(print())
                         .andReturn();
        verify(service).getReservation(any());
        String content= mr.getResponse().getContentAsString();
        List<ReservationVo> result= mapper.readValue(content, new TypeReference<>() {});

        for(int i=0; i<result.size(); i++) {
            assertEquals(list.get(i).getIreser(),result.get(i).getIreser());
        }
    }
    @Test
    @WithMockUser
    void checkNickNameTest() throws Exception {
        final int EXPECTED= 234;
        ResVo vo= new ResVo(EXPECTED);
        given(service.checkNickName(any())).willReturn(vo);
        String nickname= "test";
        MvcResult mr= mvc.perform(MockMvcRequestBuilders.post("/api/user/signup/"+nickname)
                                                        .with(csrf())
                                                        .param("nickname",nickname))
                         .andExpect(status().isOk())
                         .andDo(print())
                         .andReturn();
        verify(service).checkNickName(any());
        String content= mr.getResponse().getContentAsString();
        ResVo result= mapper.readValue(content, ResVo.class);
        assertEquals(vo.getResult(), result.getResult());

    }
    @Test
    @WithMockUser
    void getUserReviewTest() throws Exception {
        List<ReviewVo> list= new ArrayList();
        ReviewVo vo1= new ReviewVo();
        vo1.setIreview(123);
        ReviewVo vo2= new ReviewVo();
        vo2.setIreview(456);
        list.add(vo1);
        list.add(vo2);
        given(service.getUserReview(any())).willReturn(list);
        MvcResult mr= mvc.perform(MockMvcRequestBuilders.get("/api/user/review")
                                                        .with(csrf())
                                                        .param("page","1"))
                         .andExpect(status().isOk())
                         .andDo(print())
                         .andReturn();
        verify(service).getUserReview(any());
        String content= mr.getResponse().getContentAsString();
        List<ReviewVo> result= mapper.readValue(content, new TypeReference<>() {});

        for(int i=0; i<result.size(); i++) {
            assertEquals(list.get(i).getIreview(),result.get(i).getIreview());
        }
    }
    @Test
    @WithMockUser
    void getUserBookmark() throws Exception {
        List<BookmarkShopVo> list= new ArrayList();
        BookmarkShopVo vo1= new BookmarkShopVo();
        vo1.setIshop(321);
        BookmarkShopVo vo2= new BookmarkShopVo();
        vo2.setIshop(654);
        list.add(vo1);
        list.add(vo2);
        given(service.getUserBookmark(any())).willReturn(list);
        MvcResult mr= mvc.perform(MockMvcRequestBuilders.get("/api/user/bookmark")
                                                        .with(csrf())
                                                        .param("page","1"))
                         .andExpect(status().isOk())
                         .andDo(print())
                         .andReturn();
        verify(service).getUserBookmark(any());
        String content= mr.getResponse().getContentAsString();
        List<BookmarkShopVo> result= mapper.readValue(content, new TypeReference<>() {});

        for(int i=0; i<result.size(); i++) {
            assertEquals(list.get(i).getIshop(),result.get(i).getIshop());
        }
    }
    @Test
    @WithMockUser
    void delShopReview() throws Exception {
        ReviewDelDto dto= new ReviewDelDto();
        dto.setCheckShop(0);
        dto.setIreview(1);
        ResVo vo= new ResVo(66);
        given(service.delShopReview(any())).willReturn(vo);
        MvcResult mr= mvc.perform(MockMvcRequestBuilders.delete("/api/user/review")
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(mapper.writeValueAsString(dto))
                                                        .with(csrf()))
                         .andExpect(status().isOk())
                         .andDo(print())
                         .andReturn();
        verify(service).delShopReview(any());
        String content= mr.getResponse().getContentAsString();
        ResVo result= mapper.readValue(content, ResVo.class);
        assertEquals(vo.getResult(), result.getResult());
    }
}