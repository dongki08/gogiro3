package com.green.gogiro.owner.model;

import com.green.gogiro.entity.shop.ShopCategoryEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopCategoryRepository extends JpaRepository<ShopCategoryEntity,Long> {
    Optional<ShopCategoryEntity> findByImeat(long imeat);
}
