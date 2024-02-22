package com.green.gogiro.owner.model;

import com.green.gogiro.entity.butcher.ButcherReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerButcherReviewRepository extends JpaRepository<ButcherReviewEntity,Long> {
}
