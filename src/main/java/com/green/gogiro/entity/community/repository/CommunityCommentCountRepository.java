package com.green.gogiro.entity.community.repository;

import com.green.gogiro.entity.community.CommunityCommentCountEntity;
import com.green.gogiro.entity.community.CommunityCommentCountIds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityCommentCountRepository extends JpaRepository<CommunityCommentCountEntity, CommunityCommentCountIds> {
    Optional<CommunityCommentCountEntity> findByCommunityCommentCountIds(CommunityCommentCountIds commentCountEntity);
}
