package com.green.gogiro.entity.butcher.repository;

import com.green.gogiro.entity.butcher.ButcherReviewCountEntity;
import com.green.gogiro.entity.butcher.ButcherReviewCountIds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ButcherReviewCountRepository extends JpaRepository<ButcherReviewCountEntity, ButcherReviewCountIds> {
    Optional<ButcherReviewCountEntity> findByButcherReviewCountIds(ButcherReviewCountIds butcherReviewCountIds);
}
