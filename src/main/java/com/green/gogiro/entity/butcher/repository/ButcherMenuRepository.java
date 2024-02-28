package com.green.gogiro.entity.butcher.repository;

import com.green.gogiro.entity.butcher.ButcherEntity;
import com.green.gogiro.entity.butcher.ButcherMenuEntity;
import com.green.gogiro.owner.model.OwnerMenuQdslRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ButcherMenuRepository extends JpaRepository<ButcherMenuEntity,Long>, OwnerMenuQdslRepository {
    @EntityGraph(attributePaths = {"butcherEntity"})
    List<ButcherMenuEntity> findByButcherEntity(ButcherEntity butcherEntity);
    Optional<ButcherMenuEntity> findByIbutMenu(long imenu);

}
