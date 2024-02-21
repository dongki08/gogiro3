package com.green.gogiro.entity.shop.repository;

import com.green.gogiro.entity.UserEntity;
import com.green.gogiro.entity.shop.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<ShopEntity,Long> {
    ShopEntity findByUserEntity(UserEntity userEntity);
}
