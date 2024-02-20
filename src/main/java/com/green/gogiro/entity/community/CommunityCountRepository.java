package com.green.gogiro.entity.community;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityCountRepository extends JpaRepository<CommunityCountEntity, CommunityCountIds> {
    Optional<CommunityCountEntity> findAllByCommunityCountIds(CommunityCountIds countIds);

}
