package com.green.gogiro.butchershop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.gogiro.MockMvcConfig;
import com.green.gogiro.butchershop.model.ButcherSelDto;
import com.green.gogiro.butchershop.model.ButcherSelVo;
import com.green.gogiro.shop.ShopService;
import lombok.SneakyThrows;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockMvcConfig
@WebMvcTest(ButcherShopControllerTest.class)
class ButcherShopControllerTest {
//    @Autowired
//    private MockMvc mvc;
//    @MockBean
//    private ShopService service;
//    @Autowired
//    private ObjectMapper mapper;
//
//    @Test
//    void getButListTest() {
//    }
//
//    @Test
//    void postButReviewTest() {
//    }
//
//    @Test
//    void getShopDetailTest() {
//    }
}