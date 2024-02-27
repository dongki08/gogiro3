package com.green.gogiro.entity.community.repository;


import com.green.gogiro.entity.community.CommunityEntity;
import com.green.gogiro.entity.community.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommunityReportRepository extends JpaRepository<ReportEntity, Long> {

}
