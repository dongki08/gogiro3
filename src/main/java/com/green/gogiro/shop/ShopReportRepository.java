package com.green.gogiro.shop;

import com.green.gogiro.entity.community.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopReportRepository extends JpaRepository<ReportEntity, Long> {
}
