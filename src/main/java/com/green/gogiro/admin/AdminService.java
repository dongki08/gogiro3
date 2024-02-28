package com.green.gogiro.admin;


import com.green.gogiro.admin.model.*;
import com.green.gogiro.entity.butcher.ButcherEntity;
import com.green.gogiro.entity.butcher.ButcherReviewEntity;
import com.green.gogiro.entity.butcher.repository.ButcherRepository;
import com.green.gogiro.common.AppProperties;
import com.green.gogiro.common.CookieUtils;
import com.green.gogiro.common.ResVo;
import com.green.gogiro.entity.UserEntity;
import com.green.gogiro.entity.butcher.repository.ButcherReviewRepository;
import com.green.gogiro.entity.community.CommunityCommentEntity;
import com.green.gogiro.entity.community.CommunityEntity;
import com.green.gogiro.entity.community.repository.CommunityCommentRepository;
import com.green.gogiro.entity.community.repository.CommunityRepository;
import com.green.gogiro.entity.shop.ShopEntity;
import com.green.gogiro.entity.shop.ShopReviewEntity;
import com.green.gogiro.entity.shop.repository.ShopReviewRepository;
import com.green.gogiro.exception.AdminErrorCode;
import com.green.gogiro.exception.AuthErrorCode;
import com.green.gogiro.exception.CommonErrorCode;
import com.green.gogiro.exception.RestApiException;
import com.green.gogiro.security.AuthenticationFacade;
import com.green.gogiro.security.JwtTokenProvider;
import com.green.gogiro.security.MyPrincipal;
import com.green.gogiro.entity.shop.repository.ShopRepository;
import com.green.gogiro.user.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.green.gogiro.common.Const;

@Service
@RequiredArgsConstructor
public class AdminService{
    private final AdminMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AppProperties appProperties;
    private final CookieUtils cookieUtils;
    private final AuthenticationFacade authenticationFacade;
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;
    private final ShopRepository shopRepository;
    private final ButcherRepository butcherRepository;
    private final CommunityCommentRepository communityCommentRepository;
    private final ShopReviewRepository shopReviewRepository;
    private final ButcherReviewRepository butcherReviewRepository;
    //Mybatis 0.총 관리자 로그인
//    public AdminSigninVo adminSignin1(HttpServletResponse res, AdminSigninDto dto){
//        AdminSigninProcVo pVo= mapper.checkAdmin(dto.getEmail());
//        if(pVo==null){
//            throw new RestApiException(AuthErrorCode.INVALID_EXIST_USER_ID);
//        }else if(!pVo.getRole().equals("ADMIN")){
//            throw new RestApiException(CommonErrorCode.UNAUTHORIZED);
//        }else if(!passwordEncoder.matches(dto.getUpw(), pVo.getUpw())){
//            throw new RestApiException(AuthErrorCode.INVALID_PASSWORD);
//        }
//        MyPrincipal mp = new MyPrincipal();
//        mp.setIuser(pVo.getIuser());
//        mp.setRole(pVo.getRole());
//        String at = jwtTokenProvider.generateAccessToken(mp);
//        String rt = jwtTokenProvider.generateRefreshToken(mp);
//        int rtCookieMaxAge = appProperties.getJwt()
//                                          .getRefreshTokenCookieMaxAge();
//        cookieUtils.deleteCookie(res, "rt");
//        cookieUtils.setCookie(res,
//                              "rt",
//                              rt,
//                              rtCookieMaxAge
//        );
//        return AdminSigninVo.builder()
//                            .result(Const.SUCCESS)
//                            .accessToken(at)
//                            .iuser(pVo.getIuser())
//                            .build();
//    }
    //JPA 0.총 관리자 로그인
    @Transactional
    public AdminSigninVo adminSignin2(HttpServletResponse res, AdminSigninDto dto){
        Optional<UserEntity> optEntity = userRepository.findByEmail(dto.getEmail());
        UserEntity userEntity = optEntity.orElseThrow(
                ()-> new RestApiException(AuthErrorCode.INVALID_EXIST_USER_ID)//404
        );
        if(!userEntity.getRole().toString().equals("ADMIN")){
            throw new RestApiException(CommonErrorCode.UNAUTHORIZED);//401
        }else if(!passwordEncoder.matches(dto.getUpw(), userEntity.getUpw())){
            throw new RestApiException(AuthErrorCode.INVALID_PASSWORD);//404
        }
        MyPrincipal mp = new MyPrincipal();
        mp.setIuser(userEntity.getIuser());
        mp.setRole(userEntity.getRole().toString());
        mp.setCheckShop(2);
        String at = jwtTokenProvider.generateAccessToken(mp);
        String rt = jwtTokenProvider.generateRefreshToken(mp);
        int rtCookieMaxAge= appProperties.getJwt().getRefreshTokenCookieMaxAge();
        cookieUtils.deleteCookie(res, "rt");
        cookieUtils.setCookie(res, "rt", rt, rtCookieMaxAge);
        return AdminSigninVo.builder().result(Const.SUCCESS).accessToken(at).iuser(userEntity.getIuser()).checkShop(2).build();
    }
    //Mybatis 1.매장 관리 리스트
    public List<ShopVo> shopList1(ShopDto dto){
        if(!authenticationFacade.getLoginUserRole().equals("ADMIN")){
            throw new RestApiException(CommonErrorCode.UNAUTHORIZED);//401
        }
        return mapper.shopList(dto);
    }
    //JPA 1.매장 관리 리스트
//    @Transactional
//    public List<ShopVo> shopList2(){return null;}//UNION 포함
    //Mybatis 2.가게 승인 여부 변경
//    public ResVo confirmShop1(ConfirmDto dto){
//        if(!authenticationFacade.getLoginUserRole().equals("ADMIN")){
//            throw new RestApiException(CommonErrorCode.UNAUTHORIZED);
//        }
//        return new ResVo(mapper.confirmShop(dto));
//    }
    //JPA 2.가게 승인 여부 변경
    @Transactional
    public ResVo confirmShop2(ConfirmsDto dto){
        if(!authenticationFacade.getLoginUserRole().equals("ADMIN")){
            throw new RestApiException(CommonErrorCode.UNAUTHORIZED);//401
        }
        if(dto.isShop()){
            Optional<ShopEntity> optional= shopRepository.findById((long)dto.getIshop());
            ShopEntity entity= optional.orElseThrow(()->new RestApiException(AuthErrorCode.VALID_SHOP));//404
            entity.setConfirm(dto.getConfirm());
        }else{
            Optional<ButcherEntity> optional= butcherRepository.findById((long)dto.getIshop());
            ButcherEntity entity= optional.orElseThrow(()->new RestApiException(AuthErrorCode.VALID_SHOP));//404
            entity.setConfirm(dto.getConfirm());
        }
        return new ResVo(Const.SUCCESS);
    }
    //Mybatis 3.신고 글 리스트
    public List<ReportedVo> reportList1(ReportDto dto){
        if(!authenticationFacade.getLoginUserRole().equals("ADMIN")){
            throw new RestApiException(CommonErrorCode.UNAUTHORIZED);//401
        }
        return mapper.reportList(dto);
    }
    //JPA 3.신고 글 리스트
//    @Transactional
//    public List<ReportedVo> reportList2(){return null;}//UNION 포함
    //Mybatis 4.글 숨김
//    public ResVo hide1(HideDto dto){
//        if(!authenticationFacade.getLoginUserRole().equals("ADMIN")){
//            throw new RestApiException(CommonErrorCode.UNAUTHORIZED);
//        }
//        return new ResVo(mapper.hide(dto));
//    }
    //JPA 4.글 숨김
    @Transactional
    public ResVo hide2(HideDto dto){
        switch(dto.getCheck()){
            case 0:
                Optional<CommunityEntity> optional0= communityRepository.findById((long)dto.getPk());
                CommunityEntity entity0= optional0.orElseThrow(
                        ()->new RestApiException(AuthErrorCode.NOT_COMMUNITY_CHECK)//404
                );
                if(entity0.getCount()==3){
                    throw new RestApiException(AdminErrorCode.ALREADY_BLINDED);
                } else{entity0.setCount(3);}
                break;
            case 1:
                Optional<CommunityCommentEntity> optional1= communityCommentRepository.findById((long)dto.getPk());
                CommunityCommentEntity entity1= optional1.orElseThrow(
                        ()->new RestApiException(AuthErrorCode.NOT_COMMUNITY_CHECK)//404
                );
                if(entity1.getCount()==3){
                    throw new RestApiException(AdminErrorCode.ALREADY_BLINDED);
                } else{entity1.setCount(3);}
                break;
            case 2:
                Optional<ShopReviewEntity> optional2= shopReviewRepository.findById((long)dto.getPk());
                ShopReviewEntity entity2= optional2.orElseThrow(
                        ()->new RestApiException(AuthErrorCode.NOT_COMMUNITY_CHECK)//404
                );
                if(entity2.getCount()==3){
                    throw new RestApiException(AdminErrorCode.ALREADY_BLINDED);
                } else{entity2.setCount(3);}
                break;
            case 3:
                Optional<ButcherReviewEntity> optional3= butcherReviewRepository.findById((long)dto.getPk());
                ButcherReviewEntity entity3= optional3.orElseThrow(
                        ()->new RestApiException(AuthErrorCode.NOT_COMMUNITY_CHECK)//404
                );
                if(entity3.getCount()==3){
                    throw new RestApiException(AdminErrorCode.ALREADY_BLINDED);
                } else{entity3.setCount(3);}
                break;
        }
        return new ResVo(Const.SUCCESS);
    }
    //Mybatis 5.게시물 신고 취소
    /*public ResVo cancelReport1(CancelReportDto dto){
        if(!authenticationFacade.getLoginUserRole().equals("ADMIN")){
            throw new RestApiException(CommonErrorCode.UNAUTHORIZED);//401
        }
        DelCount del= new DelCount();
        del.setCheck(dto.getCheck());
        del.setPk(dto.getPk());
        mapper.delCount(del);
        return new ResVo(mapper.cancelReport(dto));
    }*/
    //JPA 5.게시물 신고 취소
    @Transactional
    public ResVo cancelReport2(CancelReportDto dto){
        switch(dto.getCheck()){
            case 0:
                Optional<CommunityEntity> optional0= communityRepository.findById((long)dto.getPk());
                CommunityEntity entity0= optional0.orElseThrow(
                        ()->new RestApiException(AuthErrorCode.NOT_COMMUNITY_CHECK)//404
                );
                if(entity0.getCount()==0){
                    throw new RestApiException(AdminErrorCode.NO_REPORTED);
                } else{entity0.setCount(0);}
                break;
            case 1:
                Optional<CommunityCommentEntity> optional1= communityCommentRepository.findById((long)dto.getPk());
                CommunityCommentEntity entity1= optional1.orElseThrow(
                        ()->new RestApiException(AuthErrorCode.NOT_COMMUNITY_CHECK)//404
                );
                if(entity1.getCount()==0){
                    throw new RestApiException(AdminErrorCode.NO_REPORTED);
                } else{entity1.setCount(0);}
                break;
            case 2:
                Optional<ShopReviewEntity> optional2= shopReviewRepository.findById((long)dto.getPk());
                ShopReviewEntity entity2= optional2.orElseThrow(
                        ()->new RestApiException(AuthErrorCode.NOT_COMMUNITY_CHECK)//404
                );
                if(entity2.getCount()==0){
                    throw new RestApiException(AdminErrorCode.NO_REPORTED);
                } else{entity2.setCount(0);}
                break;
            case 3:
                Optional<ButcherReviewEntity> optional3= butcherReviewRepository.findById((long)dto.getPk());
                ButcherReviewEntity entity3= optional3.orElseThrow(
                        ()->new RestApiException(AuthErrorCode.NOT_COMMUNITY_CHECK)//404
                );
                if(entity3.getCount()==0){
                    throw new RestApiException(AdminErrorCode.NO_REPORTED);
                } else{entity3.setCount(0);}
                break;
        }
        DelCount del= new DelCount();
        del.setCheck(dto.getCheck());
        del.setPk(dto.getPk());
        mapper.delCount(del);
        return new ResVo(Const.SUCCESS);
    }
    //Mybatis 6.사용자(USER,OWNER)블랙 리스트(정지)
    public List<BlackVo> blackList1(BlackDto dto){
        if(!authenticationFacade.getLoginUserRole().equals("ADMIN")){
            throw new RestApiException(CommonErrorCode.UNAUTHORIZED);//401
        }
        List<Integer> list= mapper.beforeBlackList();
        dto.setList(list);
        return !list.isEmpty()?mapper.blackList(dto):new ArrayList<>();
    }
    //JPA 6.사용자(USER,OWNER)블랙 리스트(정지)
//    @Transactional
//    public List<BlackVo> blackList2(){return null;}//UNION 사용
    //Mybatis 7.계정 정지/정지 해제(토글로 처리)
//    public ResVo suspendAccount1(int iuser){
//        if(!authenticationFacade.getLoginUserRole().equals("ADMIN")){
//            throw new RestApiException(CommonErrorCode.UNAUTHORIZED);
//        }
//        return new ResVo(mapper.suspendAccount(new CheckDto(iuser)));
//    }
    //JPA 7.계정 정지/정지 해제(토글로 처리)
    @Transactional
    public ResVo suspendAccount2(int iuser){
        if(!authenticationFacade.getLoginUserRole().equals("ADMIN")){
            throw new RestApiException(CommonErrorCode.UNAUTHORIZED);//401
        }
        Optional<UserEntity> optional= userRepository.findById((long)iuser);
        UserEntity entity= optional.orElseThrow(()->new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));//404
        entity.setCheck(entity.getCheck()==1?0:1);
        return new ResVo(entity.getCheck());
    }
}
