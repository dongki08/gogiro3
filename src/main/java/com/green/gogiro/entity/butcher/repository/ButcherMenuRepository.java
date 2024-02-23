package com.green.gogiro.entity.butcher.repository;

import com.green.gogiro.entity.butcher.ButcherEntity;
import com.green.gogiro.entity.butcher.ButcherMenuEntity;
import com.green.gogiro.owner.model.OwnerMenuQdslRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ButcherMenuRepository extends JpaRepository<ButcherMenuEntity,Long>, OwnerMenuQdslRepository {
    @EntityGraph(attributePaths = {"ButcherEntity"})
    List<ButcherMenuEntity> findByButcherEntity(ButcherEntity entity);

}
