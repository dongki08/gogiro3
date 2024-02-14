package com.green.gogiro.community;

import com.green.gogiro.common.Const;
import com.green.gogiro.community.model.*;
import com.green.gogiro.user.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommunityMapperTest {
    @Autowired
    private CommunityMapper mapper;

    @Autowired
    private UserMapper userMapper;


    @Test
    void insCommunity() {
        int iuser = userMapper.selIuserForTest();
        CommunityInsDto dto = new CommunityInsDto();
        //insCommunity 테스트
        dto.setIuser(iuser);
        String title = "제목";
        dto.setTitle(title);
        String contents = "내용";
        dto.setContents(contents);
        //insCommunityPics 테스트
        List<String> pics = new ArrayList<>();
        pics.add("aa");
        pics.add("bb");
        dto.setPics(pics);

        //insCommunity 확인
        assertEquals(Const.SUCCESS, mapper.insCommunity(dto));
        //auto_increment 확인
        assertTrue(dto.getIboard() > 0);
        //insCommunityPics 확인
        assertEquals(pics.size(), mapper.insCommunityPics(dto));

        //beforeAfterTitle 테스트
        CommunitySelBeAfDto result = mapper.beforeTitle(dto.getIboard());
        CommunitySelBeAfDto result2 = mapper.afterTitle(result.getIboard());
        assertEquals(result2.getTitle(), dto.getTitle());

        //selByCommunityPics 테스트
        List<CommunityBySelPicsDto> list = mapper.selByCommunityPics(dto.getIboard());
        List<Integer> i = new ArrayList<>();
        for(CommunityBySelPicsDto z : list){
            i.add(z.getIcommuPics());
        }

        //selCommunityPics 테스트
        List<CommunityBySelPicsDto> list2 = mapper.selCommunityPics(i);
        assertEquals(list.size(), pics.size());
        assertEquals(list2.size(), pics.size());
        for(int j = 0; j < pics.size(); j++) {
            assertEquals(pics.get(j), list.get(j).getPic());
            assertEquals(pics.get(j), list2.get(j).getPic());
        }

    }

    @Test
    void updCommunity() {
        CommunityUpdDto dto = mapper.selIuserIboardForTest();
        //updCommunity 테스트
        String title = "제목";
        dto.setTitle(title);
        String contents = "내용";
        dto.setContents(contents);

        //insCommunityPics(upd) 테스트
        List<String> pics = new ArrayList<>();
        pics.add("aa");
        pics.add("bb");
        dto.setPics(pics);

        //updCommunity 확인
        assertEquals(Const.SUCCESS, mapper.updCommunity(dto));
        //insCommunityPics(upd) 확인
        assertEquals(pics.size(), mapper.insCommunityPics(dto));

        //delCommunityPic 테스트
        int result = mapper.selCommunityDel1();
        List<Integer> selicommuPics = mapper.selCommunityDel2(result);
        int result2 = mapper.delCommunityDel(result);
        //delCommunityPic 삭제된 갯수와 사진 pk갯수 확인
        assertEquals(result2, selicommuPics.size());
        List<Integer> selicommuPics2 = mapper.selCommunityDel2(result);
        //delCommunityPic 0개, 사진 pk갯수 2차 확인
        assertEquals(0, selicommuPics2.size());
    }

//    @Test
//    void selCommunityCount() {
//        int result = mapper.selCommunityCount();
//
//    }


    @Test
    void selCommunityPics() {

    }

    @Test
    void selCommunity() {
    }

    @Test
    void selByCommunityPics() {
    }

    @Test
    void selPicCommunity() {
    }

    @Test
    void selCommunityComments() {
    }

    @Test
    void selDetailCommunity() {
    }

    @Test
    void entityCommunity() {
    }

    @Test
    void delCommunity() {
    }

    @Test
    void testDelCommunityPic() {
    }

    @Test
    void delCommunityAllComment() {
    }

    @Test
    void checkCommunity() {
    }

    @Test
    void insCommunityComment() {
    }

    @Test
    void delCommunityComment() {
    }
}