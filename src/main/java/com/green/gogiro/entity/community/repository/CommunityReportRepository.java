package com.green.gogiro.entity.community.repository;


import com.green.gogiro.entity.community.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityReportRepository extends JpaRepository<ReportEntity, Long> {

}
