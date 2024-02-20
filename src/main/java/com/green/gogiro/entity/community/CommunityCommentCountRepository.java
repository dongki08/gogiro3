package com.green.gogiro.entity.community;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityCommentCountRepository extends JpaRepository<CommunityCommentCountEntity, CommunityCommentCountIds> {
    Optional<CommunityCommentCountEntity> findByCommunityCommentCountIds(CommunityCommentCountIds commentCountEntity);
}
