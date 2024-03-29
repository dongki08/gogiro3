package com.green.gogiro.entity.shop.repository;

import com.green.gogiro.entity.shop.ShopEntity;
import com.green.gogiro.entity.shop.ShopReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopReviewRepository extends JpaRepository<ShopReviewEntity, Long> {
}
