package com.green.gogiro.entity.community.repository;

import com.green.gogiro.entity.community.CommunityCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityCommentRepository extends JpaRepository<CommunityCommentEntity, Long> {
}
