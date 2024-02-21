package com.green.gogiro.owner.model;

import com.green.gogiro.entity.shop.ShopReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerShopReviewRepository extends JpaRepository<ShopReviewEntity,Long>, ReviewQdslRepository{
}
