package com.green.gogiro.community;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.gogiro.MockMvcConfig;
import com.green.gogiro.common.ResVo;
import com.green.gogiro.community.model.CommunityDelDto;
import com.green.gogiro.community.model.CommunityInsDto;
import com.green.gogiro.community.model.CommunityPicsInsVo;
import com.green.gogiro.community.model.CommunitySelVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockMvcConfig
//@WebMvcTest(CommunityController.class)
public class CommunityControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommunityService service;

    @Autowired
    private ObjectMapper mapper;

    private MockMultipartFile mockMultipartFile;
    @BeforeEach
    public void before() throws Exception {
        String fileNm = "cat.jpg";
        String filePath = "D:/home/download/gksk/"+ fileNm;
        FileInputStream fis = new FileInputStream(filePath);
        this.mockMultipartFile = new MockMultipartFile("pic", fileNm, "jpg", fis);
    }
    @Test
    void postCommunity() throws Exception {



        CommunityInsDto dto = new CommunityInsDto();
        dto.setIuser(4);
        dto.setTitle("dd");
        dto.setContents("내용");
        List<String> pics = new ArrayList<>();
        pics.add("aa.jpg");
        CommunityPicsInsVo vo = CommunityPicsInsVo.builder()
                .pics(pics)
                .build();

        given(service.insCommunity(any())).willReturn(vo);
        dto.setPics(pics);

        mvc.perform(
                MockMvcRequestBuilders
                        .post("/api/community")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto))
                )
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(vo)))
                .andDo(print());
        verify(service).insCommunity(any());
    }


    @Test
    void getCommunity() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", "1");

        CommunitySelVo vo = new CommunitySelVo();
        vo.setIboard(1);
        vo.setBoardNum(1);
        vo.setIuser(4);
        vo.setWriterName("누구야");
        vo.setTitle("제목");
        vo.setContents("내용");
        List<String> pics3 = new ArrayList<>();
        pics3.add("d");
        pics3.add("q");
        vo.setPics(pics3);

        CommunitySelVo vo2 = new CommunitySelVo();
        vo2.setIboard(2);
        vo2.setBoardNum(2);
        vo2.setIuser(4);
        vo2.setWriterName("1누구야");
        vo2.setTitle("1제목");
        vo2.setContents("1내용");
        List<String> pics4 = new ArrayList<>();
        pics4.add("d");
        pics4.add("q");
        vo2.setPics(pics4);

        List<CommunitySelVo> list = new ArrayList<>();
        list.add(vo);
        list.add(vo2);
        given(service.selCommunity(any())).willReturn(list);


        mvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/community")
                                .params(params)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(list)))
                .andDo(print());

        verify(service).selCommunity(any());
    }



    @Test
    void delCommunity() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("iuser", "4");
        params.add("iboard", "1");
        ResVo result = new ResVo(1);
        CommunityDelDto dto = new CommunityDelDto();
        dto.setIuser(4);
        dto.setIboard(1);
        given(service.delCommunity(dto)).willReturn(result);

        mvc.perform(
                        MockMvcRequestBuilders.delete("/api/community")
                                .params(params)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(result)))
                .andDo(print());


        verify(service).delCommunity(any());
    }




}
