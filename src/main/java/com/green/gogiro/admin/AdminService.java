package com.green.gogiro.admin;


import com.green.gogiro.admin.model.*;
import com.green.gogiro.butchershop.ButcherRepository;
import com.green.gogiro.common.ResVo;
import com.green.gogiro.entity.community.CommunityRepository;
import com.green.gogiro.shop.ShopRepository;
import com.green.gogiro.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService{
    private final AdminMapper mapper;
    private final ShopRepository shopRepository;
    private final ButcherRepository butcherRepository;
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;
    //Mybatis 1.매장 관리 리스트
    public List<ShopVo> shopList1(){return mapper.shopList();}
    //JPA 1.매장 관리 리스트
    @Transactional
    public List<ShopVo> shopList2(){
        //UNION 포함
        return null;
    }
    //Mybatis 2.가게 승인 여부 변경
    public ResVo confirmShop1(ConfirmDto dto){return new ResVo(mapper.confirmShop(dto));}
    @Transactional
    //JPA 2.가게 승인 여부 변경
    public ResVo confirmShop2(){return null;}
    //Mybatis 3.신고 글 리스트
    public List<ReportedVo> reportList1(){

        return null;
    }
    //JPA 3.신고 글 리스트
    @Transactional
    public List<ReportedVo> reportList2(){
        //UNION 포함
        return null;
    }
    //Mybatis 4.글 숨김
    public ResVo hide1(@RequestBody HideDto dto){return null;}
    //JPA 4.글 숨김
    @Transactional
    public ResVo hide2(@RequestBody HideDto dto){return null;}
    //Mybatis 5.게시물 신고 취소
    public ResVo cancelReport1(CancelReportDto dto){return null;}
    //JPA 5.게시물 신고 취소
    @Transactional
    public ResVo cancelReport2(CancelReportDto dto){return null;}
    //Mybatis 6.사용자(USER,OWNER)블랙 리스트(정지)
    public List<BlackVo> blackList1(){return null;}
    //JPA 6.사용자(USER,OWNER)블랙 리스트(정지)
    @Transactional
    public List<BlackVo> blackList2(){return null;}
    //Mybatis 7.계정 정지/정지 해제(토글로 처리)
    public ResVo suspendAccount1(int iuser){return null;}
    //JPA 7.계정 정지/정지 해제(토글로 처리)
    @Transactional
    public ResVo suspendAccount2(int iuser){return null;}
}
