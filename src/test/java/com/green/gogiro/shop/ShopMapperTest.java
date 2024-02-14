package com.green.gogiro.shop;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.*;
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ShopMapperTest {
    @Autowired
    private ShopMapper shopMapper;

    @Test
    void insShopReview() {
    }

    @Test
    void insShopReviewPic() {
    }


    @Test
    void shopBookmarkOn() {
    }

    @Test
    void shopBookmarkOff() {
    }
}