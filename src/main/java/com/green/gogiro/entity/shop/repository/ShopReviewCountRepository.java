package com.green.gogiro.entity.shop.repository;

import com.green.gogiro.entity.shop.ShopReviewCountEntity;
import com.green.gogiro.entity.shop.ShopReviewCountIds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopReviewCountRepository extends JpaRepository<ShopReviewCountEntity, ShopReviewCountIds> {
    Optional<ShopReviewCountEntity> findByShopReviewCountIds(ShopReviewCountIds shopReviewCountIds);
}
