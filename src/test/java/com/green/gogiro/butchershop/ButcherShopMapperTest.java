package com.green.gogiro.butchershop;

import com.green.gogiro.butchershop.model.*;
import com.green.gogiro.shop.model.ShopDetailVo;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ButcherShopMapperTest {
    @Autowired
    private ButcherShopMapper mapper;

    @Test
    void selButcherShopAllTest() throws Exception{
        ButcherSelDto dto= new ButcherSelDto();
        dto.setPage(1);

        List<ButcherSelVo> result= mapper.selButcherShopAll(dto);

        assertEquals(2, result.size());
    }

    @Test
    void selButcherShopPicListTest() throws Exception {
        List<Integer> butcherShop= new ArrayList<>();
        butcherShop.add(1);
        butcherShop.add(2);
        List<ButcherPicsVo> pics= mapper.selButcherShopPicList(butcherShop);
        int count= 0;
        for(Integer ibutcher: butcherShop) {
            List<String> picList= mapper.selButcherPicsForTest(ibutcher);
            count+= picList.size();
        }
        assertEquals(count,pics.size());
    }



    @Test
    void selMenuDetailTest() throws Exception {


    }

    @Test
    void selReviewDetailTest() throws Exception {
        ReviewDetail detail = new ReviewDetail();
        detail.setIreview(76);
        detail.setNickname("기영이");
        detail.setIbutcher(1);
        detail.setStar(5);
        detail.setReview("ddddd");
        ReviewDetail detail2 = new ReviewDetail();
        detail2.setIreview(77);
        detail2.setNickname("기영이");
        detail2.setIbutcher(1);
        detail2.setStar(5);
        detail2.setReview("ddddd");

        List<ReviewDetail> vo = new ArrayList<>();
        vo.add(detail);
        vo.add(detail2);

        List<ReviewDetail> vo2 = mapper.selReviewDetail(1);
        assertEquals(vo,vo2);
    }

    @Test
    void selReviewPicDetailTest() throws Exception {

        ReviewPicVo pic = new ReviewPicVo();
        pic.setIreview(76);
        pic.setPic("da118ea5-6563-41a9-a362-2c12e7e56f24.jpg");
        ReviewPicVo pic2 = new ReviewPicVo();
        pic2.setIreview(77);
        pic2.setPic("1af4609b-65e8-4d9a-a6f8-37708bed764a.jpg");

        List<ReviewPicVo> vo2 = new ArrayList<>();
        vo2.add(pic);
        vo2.add(pic2);
        List<ReviewPicVo> vo = mapper.selReviewPicDetail(1);
        assertEquals(vo2,vo);
        //da118ea5-6563-41a9-a362-2c12e7e56f24.jpg
        //1af4609b-65e8-4d9a-a6f8-37708bed764a.jpg
    }


    @Test
    void selButcherShopAll() throws  Exception{
        List<String> pics = new ArrayList<>();
       // pics.add("https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20220116_288%2F1642311643318kwPLB_JPEG%2F85725C4B-61E2-4812-84F4-2787055384F3.jpeg");
       // pics.add("https://search.pstatic.net/common/?src=https%3A%2F%2Fpup-review-phinf.pstatic.net%2FMjAyMzExMDZfMjA2%2FMDAxNjk5MjY1ODg2OTMx.SzaZp3I0Jnoi2aQo27jxjJ7YHRL0_BZrmq3LGVN_mRMg.r8eb55qSiFzhxtmeHzUq6ryWqUlk6v8Jx_Z1_8xIUhog.JPEG%2F20231106_191705.jpg");
       // pics.add("https://search.pstatic.net/common/?src=https%3A%2F%2Fpup-review-phinf.pstatic.net%2FMjAyMzEwMTJfMiAg%2FMDAxNjk3MDcxMzk5OTA1.8dI82VAahXt7C-bJ-GkZj86jP3rYwPWuTpOnu40gLD0g.Nwsww0Qs13mJIvMLvRvWi_lSSsyrHcw1RY4Xhxfwmtog.JPEG%2F20231011_164501.jpg");
       // pics.add("https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20231002_284%2F16962130346305vjqz_JPEG%2F1000003022.jpg");
       // pics.add("https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20200215_156%2F1581763779726ttzb6_JPEG%2Fw2PQLx9eCUl-gV6J8A0-utOU.jpg");
        ButcherSelVo vo = new ButcherSelVo();
        vo.setIbutcher(2);
        vo.setName("남문한우백화점 부림축산");
        vo.setLocation("대구 중구 중앙대로 307 남문시장 1층");
        vo.setCount(0);
        //vo.setPics(pics);
        ButcherSelVo vo2 = new ButcherSelVo();
        vo2.setIbutcher(2);
        vo2.setName("고기");

        ButcherSelDto dto = new ButcherSelDto();
        dto.setSearch("");
        dto.setPage(1);
        dto.setStartIdx(1);

        List<ButcherSelVo> list = new ArrayList<>();
        list.add(vo);
        List<ButcherSelVo> list2 = mapper.selButcherShopAll(dto);

        assertEquals(list,list2);

    }



    @Test
    void selButcherShopPics() throws  Exception {
    }

    @Test
    void insButcherReview() throws  Exception{
    }

    @Test
    void insButcherReviewPic() throws  Exception{
    }

    @Test
    void selButcherShopDetail() throws  Exception{
    ButcherShopDetailVo vo = new ButcherShopDetailVo();
    vo.setIbutcher(1);
    vo.setName("다정축산물직판장");
    vo.setLocation("대구 중구 중앙대로 314");
    vo.setOpen("매일\r\n08:30 - 22:30");
    vo.setTel("0507-1393-9410");
    vo.setX("126.726385532141");
    vo.setY("35.1804742090837");
    vo.setStar(5);
    vo.setIsBook(0);
    ButDto dto = new ButDto(0,1);
    ButcherShopDetailVo vo2 = mapper.selButcherShopDetail(dto);
    assertEquals(vo,vo2);
    }

    @Test
    void selMenuDetail() throws  Exception {
    }


    @Test
    void selReviewDetail() throws  Exception {
    }


    @Test
    void selReviewPicDetail() throws  Exception {
    }



    @Test
    void selButcherPicsForTest() throws  Exception {
    }

    @Test
    void selButcherReviewForTest() throws  Exception {
    }

    @Test
    void selButcherBookmark() throws  Exception {
    }


}