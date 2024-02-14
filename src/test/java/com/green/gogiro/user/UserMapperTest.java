package com.green.gogiro.user;

import com.green.gogiro.butchershop.ButcherShopMapper;
import com.green.gogiro.reservation.ReservationMapper;
import com.green.gogiro.reservation.model.PickupInsDto;
import com.green.gogiro.reservation.model.PickupMenuDto;
import com.green.gogiro.reservation.model.ReservationInsDto;
import com.green.gogiro.shop.ShopMapper;
import com.green.gogiro.user.model.*;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import static org.junit.jupiter.api.Assertions.*;
import com.green.gogiro.common.Const;

import java.util.ArrayList;
import java.util.List;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserMapperTest {
    @Autowired
    private UserMapper mapper;


    @Test
    void signupUserTest() {
        UserSignupDto dto = new UserSignupDto();
        String email= "bnlfgnb@sln.cmo";
        dto.setEmail(email);
        String upw= "fnonvoif";
        dto.setUpw(upw);
        String name= "카카카";
        dto.setName(name);
        String nickname= "파파파파파";
        dto.setNickname(nickname);
        String birth= "1234-11-11";
        dto.setBirth(birth);
        String gender= "남";
        dto.setGender(gender);
        String address="라라";
        dto.setAddress(address);

        dto.setTel("010-4848-9898");

        assertEquals(Const.SUCCESS, mapper.signupUser(dto));
        assertTrue(dto.getIuser()>0);

        UserInfoVo vo= mapper.selUserInfo(dto.getIuser());

        assertEquals(email,vo.getEmail());
        assertEquals(name,vo.getName());
        assertEquals(nickname,mapper.checkNickname(vo.getNickname()));
        assertEquals(birth,vo.getBirth());
        assertEquals(gender,vo.getGender());
        assertEquals(address,vo.getAddress());
    }

    @Test
    void updUserPicTest() {
        int iuser= mapper.selIuserForTest();

        UserInfoVo before= mapper.selUserInfo(iuser);
        String pic= (before.getPic()==null)? "abc.jpg": null;

        UserSignupDto dto = new UserSignupDto();
        dto.setIuser(iuser);
        dto.setPic(pic);

        assertEquals(Const.SUCCESS, mapper.updUserPic(dto));

        UserInfoVo result= mapper.selUserInfo(iuser);
        assertEquals(pic, result.getPic());
    }

    @Test
    void updateUserTest() {
        int iuser= mapper.selIuserForTest();

        UserInfoVo before= mapper.selUserInfo(iuser);
        String nickname= "테스트용 닉네임";
        String address= "테스트용 주소";
        String pic= (before.getPic()==null)? "abc.jpg": null;
        String tel= "010-1234-5678";

        UserUpdDto dto = new UserUpdDto();
        dto.setIuser(iuser);
        dto.setNickname(nickname);
        dto.setAddress(address);
        dto.setPic(pic);
        dto.setTel(tel);

        assertEquals(Const.SUCCESS, mapper.updateUser(dto));

        UserInfoVo after= mapper.selUserInfo(iuser);
        assertEquals(nickname, after.getNickname());
        assertEquals(address, after.getAddress());
        assertEquals(pic, after.getPic());
        assertEquals(tel, after.getTel());
    }



    @Test
    void delShopReviewTest() {
        if(mapper.selShopReviewPicForTest1()!=null) {
            ReviewTestVo shopReview = mapper.selShopReviewPicForTest2(mapper.selShopReviewPicForTest1());
            ReviewDelDto delShopReview = new ReviewDelDto();
            delShopReview.setIuser(shopReview.getIuser());
            delShopReview.setIreview(shopReview.getIreview());
            delShopReview.setCheckShop(0);
            ReviewPk pk1 = new ReviewPk(0, shopReview.getIreview());
            List<String> shopReviewPics = mapper.selUserReviewPic(pk1);
            int delShopReviewPic = mapper.delShopReviewPics(delShopReview);
            int delShopReviewCnt = mapper.delShopReview(delShopReview);
            assertEquals(shopReviewPics.size(), delShopReviewPic);
            assertEquals(1, delShopReviewCnt);
            assertNotEquals(shopReview.getIreview(), mapper.selShopReviewPicForTest1());
        }
        if(mapper.selButReviewPicForTest1()!=null) {
            ReviewTestVo butcherReview = mapper.selButReviewPicForTest2(mapper.selButReviewPicForTest1());
            ReviewDelDto delButcherReview = new ReviewDelDto();
            delButcherReview.setIuser(butcherReview.getIuser());
            delButcherReview.setIreview(butcherReview.getIreview());
            delButcherReview.setCheckShop(1);
            ReviewPk pk2 = new ReviewPk(1, butcherReview.getIreview());
            List<String> butcherReviewPics = mapper.selUserReviewPic(pk2);
            int delButcherReviewPic = mapper.delShopReviewPics(delButcherReview);
            int delButcherReviewCnt = mapper.delShopReview(delButcherReview);
            assertEquals(butcherReviewPics.size(), delButcherReviewPic);
            assertEquals(1, delButcherReviewCnt);
            assertNotEquals(butcherReview.getIreview(), mapper.selButReviewPicForTest1());
        }
    }
}