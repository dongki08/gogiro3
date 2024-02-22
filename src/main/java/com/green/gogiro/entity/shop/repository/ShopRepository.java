package com.green.gogiro.entity.shop.repository;

import com.green.gogiro.entity.UserEntity;
import com.green.gogiro.entity.shop.ShopEntity;
import com.green.gogiro.owner.model.OwnerMainQdslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<ShopEntity,Long>, OwnerMainQdslRepository {
    ShopEntity findByUserEntity(UserEntity userEntity);
}
