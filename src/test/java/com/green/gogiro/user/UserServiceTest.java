package com.green.gogiro.user;

import com.green.gogiro.common.*;
import com.green.gogiro.security.AuthenticationFacade;
import com.green.gogiro.security.JwtTokenProvider;
import com.green.gogiro.shop.ShopMapper;
import com.green.gogiro.user.model.*;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)//스프링 컨테이너 올리기
@Import({UserService.class})//얘는 빈 등록을 좀 해 줘
class UserServiceTest {
    @Autowired
    private UserService service;
    @MockBean
    private UserMapper mapper;
    @MockBean
    private ShopMapper shopMapper;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private JwtTokenProvider jwtTokenProvider;
    @MockBean
    private AppProperties appProperties;
    @MockBean
    private CookieUtils cookeUtils;
    @MockBean
    private AuthenticationFacade authenticationFacade;
    @MockBean
    private MyFileUtils myFileUtils;

    @Test
    void signupTest() throws Exception {
        UserSignupDto dto= new UserSignupDto();
        try {
            service.signup(dto);
        } catch (NullPointerException e) {
            verify(mapper).checkNickname(any());
        }
    }

    @Test
    void checkNickNameTest() throws Exception {
        try {
            ResVo vo = service.checkNickName("zzz");
            assertEquals(1, vo.getResult());
        } catch (NullPointerException e) {
        } finally {
            verify(mapper).checkNickname(any());
        }
    }

    @Test
    void updateUserTest() throws Exception {
        UserUpdDto dto= new UserUpdDto();
        ResVo vo= service.updateUser(dto);
        verify(authenticationFacade).getLoginUserPk();
        verify(mapper).updateUser(any());
        assertEquals(1, vo.getResult());
    }

    @Test
    void selUserInfoTest() throws Exception {
        UserInfoVo vo= service.selUserInfo();
        assertNull(vo);
        verify(authenticationFacade).getLoginUserPk();
        verify(mapper).selUserInfo(0);
    }

    @Test
    void getReservationTest() throws Exception {
        UserMyPageDto dto= new UserMyPageDto();
        List<ReservationVo> list= service.getReservation(dto);
        verify(authenticationFacade).getLoginUserPk();
        verify(mapper).selUserReservationCount(0);
        verify(mapper).selReservation(any());
        assertEquals(0,list.size());
    }

    @Test
    void getUserReviewTest() throws Exception {
        UserMyPageDto dto= new UserMyPageDto();
        List<ReviewVo> list= service.getUserReview(dto);
        verify(authenticationFacade).getLoginUserPk();
        verify(mapper).selUserReviewCount(0);
        verify(mapper).selUserReview(any());
        assertEquals(0,list.size());
    }

    @Test
    void getUserBookmarkTest() throws Exception {
        UserMyPageDto dto= new UserMyPageDto();
        List<BookmarkShopVo> list= service.getUserBookmark(dto);
        verify(authenticationFacade).getLoginUserPk();
        verify(mapper).selUserBookmark(any());
        verify(mapper).selUserBookmarkCount(0);
        assertEquals(0,list.size());
    }
    @Test
    void delShopReview() throws Exception {
        ReviewDelDto dto= new ReviewDelDto();
        ResVo result= service.delShopReview(dto);
        verify(authenticationFacade).getLoginUserPk();
        verify(mapper).delShopReviewPics(any());
        verify(mapper).delShopReview(any());
        assertEquals(0, result.getResult());
    }
}