package com.green.gogiro.entity.butcher.repository;

import com.green.gogiro.entity.UserEntity;
import com.green.gogiro.entity.butcher.ButcherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ButcherRepository extends JpaRepository<ButcherEntity,Long> {
    ButcherEntity findByUserEntity(UserEntity entity);


}
