package com.green.gogiro.entity.community.repository;

import com.green.gogiro.entity.community.CommunityCountEntity;
import com.green.gogiro.entity.community.CommunityCountIds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityCountRepository extends JpaRepository<CommunityCountEntity, CommunityCountIds> {
    Optional<CommunityCountEntity> findByCommunityCountIds(CommunityCountIds countIds);

}
