package com.green.gogiro.entity.community.repository;

import com.green.gogiro.entity.community.CommunityCommentEntity;
import com.green.gogiro.entity.community.CommunityEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommunityCommentRepository extends JpaRepository<CommunityCommentEntity, Long> {
    @EntityGraph(attributePaths = {"CommentEntity"})
    List<CommunityCommentEntity> findAllByCommunityEntity(CommunityEntity entity);

    Optional<CommunityCommentEntity> findAllByIcomment(long icomment);
}
