package com.green.gogiro.user;

import com.green.gogiro.user.model.ReservationVo;
import com.green.gogiro.user.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    int signupUser(UserSignupDto dto);

    int updUserPic(UserSignupDto dto);

    int selIuserForTest();
    String checkNicknameBeforeUpdate(int iuser);
    int updateUser(UserUpdDto dto);

    UserInfoVo selUserInfo(int iuser);

    UserEntity userEntity(String email);

    String checkNickname(String nickname);

    List<ReservationVo> selReservation(UserMyPageDto dto);

    int selUserReservationCount(int iuser);

    List<ReviewVo> selUserReview(UserMyPageDto dto);

    int selUserReviewCount(int iuser);

    List<String> selUserReviewPic(ReviewPk pk);

    List<BookmarkShopVo> selUserBookmark(UserMyPageDto dto);

    int selUserBookmarkCount(int iuser);

    int delShopReviewPics(ReviewDelDto dto);

    int delShopReview(ReviewDelDto dto);

    String checkEmail(String email);

    Integer selShopReviewPicForTest1();

    ReviewTestVo selShopReviewPicForTest2(int ireview);

    Integer selButReviewPicForTest1();

    ReviewTestVo selButReviewPicForTest2(int ireview);

}
