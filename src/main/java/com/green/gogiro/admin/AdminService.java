package com.green.gogiro.admin;


import com.green.gogiro.admin.model.*;
import com.green.gogiro.entity.butcher.repository.ButcherRepository;
import com.green.gogiro.common.AppProperties;
import com.green.gogiro.common.CookieUtils;
import com.green.gogiro.common.ResVo;
import com.green.gogiro.entity.UserEntity;
import com.green.gogiro.entity.community.repository.CommunityRepository;
import com.green.gogiro.exception.AuthErrorCode;
import com.green.gogiro.exception.CommonErrorCode;
import com.green.gogiro.exception.RestApiException;
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
import java.util.List;
import java.util.Optional;
import com.green.gogiro.common.Const;

@Service
@RequiredArgsConstructor
public class AdminService{
    private final AdminMapper mapper;
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;
    private final ShopRepository shopRepository;
    private final ButcherRepository butcherRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AppProperties appProperties;
    private final CookieUtils cookieUtils;

    //Mybatis 0.총 관리자 로그인
    public AdminSigninVo adminSignin1(HttpServletResponse res, AdminSigninDto dto){
        AdminSigninProcVo pVo= mapper.checkAdmin(dto.getEmail());
        if(pVo==null){
            throw new RestApiException(AuthErrorCode.INVALID_EXIST_USER_ID);
        }else if(!pVo.getRole().equals("ADMIN")){
            throw new RestApiException(CommonErrorCode.UNAUTHORIZED);
        }else if(!passwordEncoder.matches(dto.getUpw(), pVo.getUpw())){
            throw new RestApiException(AuthErrorCode.INVALID_PASSWORD);
        }
        MyPrincipal mp = new MyPrincipal();
        mp.setIuser(pVo.getIuser());
        mp.setRole(pVo.getRole());
        String at = jwtTokenProvider.generateAccessToken(mp);
        String rt = jwtTokenProvider.generateRefreshToken(mp);
        int rtCookieMaxAge = appProperties.getJwt()
                                          .getRefreshTokenCookieMaxAge();
        cookieUtils.deleteCookie(res, "rt");
        cookieUtils.setCookie(res,
                              "rt",
                              rt,
                              rtCookieMaxAge
        );
        return AdminSigninVo.builder()
                            .result(Const.SUCCESS)
                            .accessToken(at)
                            .iuser(pVo.getIuser())
                            .build();
    }
    //JPA 0.총 관리자 로그인
    @Transactional
    public AdminSigninVo adminSignin2(HttpServletResponse res, AdminSigninDto dto){
        Optional<UserEntity> optEntity = userRepository.findByEmail(dto.getEmail());
        UserEntity userEntity = optEntity.orElseThrow(()->
                new RestApiException(AuthErrorCode.INVALID_EXIST_USER_ID)
        );
        if(!userEntity.getRole().toString().equals("ADMIN")){
            throw new RestApiException(CommonErrorCode.UNAUTHORIZED);
        }else if(!passwordEncoder.matches(dto.getUpw(), userEntity.getUpw())){
            throw new RestApiException(AuthErrorCode.INVALID_PASSWORD);
        }
        MyPrincipal mp = new MyPrincipal();
        mp.setIuser(userEntity.getIuser());
        mp.setRole(userEntity.getRole().toString());
        String at = jwtTokenProvider.generateAccessToken(mp);
        String rt = jwtTokenProvider.generateRefreshToken(mp);
        int rtCookieMaxAge= appProperties.getJwt()
                                          .getRefreshTokenCookieMaxAge();
        cookieUtils.deleteCookie(res, "rt");
        cookieUtils.setCookie(res,
                              "rt",
                              rt,
                              rtCookieMaxAge
        );
        return AdminSigninVo.builder()
                            .result(Const.SUCCESS)
                            .accessToken(at)
                            .iuser(userEntity.getIuser())
                            .build();
    }
    //Mybatis 1.매장 관리 리스트
    public List<ShopVo> shopList1(String search){return mapper.shopList(search);}
    //JPA 1.매장 관리 리스트
    @Transactional
    public List<ShopVo> shopList2(){
        //UNION 포함
        return null;
    }
    //Mybatis 2.가게 승인 여부 변경
    public ResVo confirmShop1(ConfirmDto dto){return new ResVo(mapper.confirmShop(dto));}
    //JPA 2.가게 승인 여부 변경
    @Transactional
    public ResVo confirmShop2(){return null;}
    //Mybatis 3.신고 글 리스트
    public List<ReportedVo> reportList1(int check){
        return mapper.reportList(new ReportDto(check));
    }
    //JPA 3.신고 글 리스트
    @Transactional
    public List<ReportedVo> reportList2(){
        //UNION 포함
        return null;
    }
    //Mybatis 4.글 숨김
    public ResVo hide1(@RequestBody HideDto dto){return new ResVo(mapper.hide(dto));}
    //JPA 4.글 숨김
    @Transactional
    public ResVo hide2(@RequestBody HideDto dto){return null;}
    //Mybatis 5.게시물 신고 취소
    public ResVo cancelReport1(CancelReportDto dto){return new ResVo(mapper.cancelReport(dto));}
    //JPA 5.게시물 신고 취소
    @Transactional
    public ResVo cancelReport2(CancelReportDto dto){return null;}
    //Mybatis 6.사용자(USER,OWNER)블랙 리스트(정지)
    public List<BlackVo> blackList1(){return mapper.blackList();}
    //JPA 6.사용자(USER,OWNER)블랙 리스트(정지)
    @Transactional
    public List<BlackVo> blackList2(){return null;}
    //Mybatis 7.계정 정지/정지 해제(토글로 처리)
    public ResVo suspendAccount1(int iuser){return new ResVo(mapper.suspendAccount(new CheckDto(iuser)));}
    //JPA 7.계정 정지/정지 해제(토글로 처리)
    @Transactional
    public ResVo suspendAccount2(int iuser){return null;}
}
