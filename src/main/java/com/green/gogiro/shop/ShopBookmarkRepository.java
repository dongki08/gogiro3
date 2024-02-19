package com.green.gogiro.shop;

import com.green.gogiro.common.ResVo;
import com.green.gogiro.entity.shop.ShopBookmarkEntity;
import com.green.gogiro.entity.shop.ShopBookmarkIds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopBookmarkRepository extends JpaRepository<ShopBookmarkEntity, ShopBookmarkIds> {
}
