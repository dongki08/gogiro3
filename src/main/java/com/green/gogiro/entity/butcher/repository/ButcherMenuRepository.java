package com.green.gogiro.entity.butcher.repository;

import com.green.gogiro.entity.butcher.ButcherEntity;
import com.green.gogiro.entity.butcher.ButcherMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ButcherMenuRepository extends JpaRepository<ButcherMenuEntity,Long>{
    List<ButcherMenuEntity> findByButcherEntity(ButcherEntity entity);
}
