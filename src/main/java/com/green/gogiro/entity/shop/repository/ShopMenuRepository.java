package com.green.gogiro.entity.shop.repository;

import com.green.gogiro.entity.shop.ShopEntity;
import com.green.gogiro.entity.shop.ShopMenuEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopMenuRepository extends JpaRepository<ShopMenuEntity,Long> {
    @EntityGraph(attributePaths = {"ShopEntity"})
    List<ShopMenuEntity> findByShopEntity(ShopEntity shopEntity);
}
