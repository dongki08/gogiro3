package com.green.gogiro.entity.shop.repository;

import com.green.gogiro.entity.community.ReportEntity;
import com.green.gogiro.entity.shop.ShopReviewCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopReportRepository extends JpaRepository<ReportEntity, Long> {
}
