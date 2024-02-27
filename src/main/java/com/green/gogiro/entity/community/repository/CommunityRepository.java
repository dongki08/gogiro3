package com.green.gogiro.entity.community.repository;

import com.green.gogiro.entity.community.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityRepository extends JpaRepository<CommunityEntity, Long> {
    Optional<CommunityEntity> findAllByIboard(long iboard);
}
