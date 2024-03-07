package com.green.gogiro.entity.community.repository;

import com.green.gogiro.community.model.CommunitySelVo;
import com.green.gogiro.entity.UserEntity;
import com.green.gogiro.entity.community.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommunityRepository extends JpaRepository<CommunityEntity, Long> {
    Optional<CommunityEntity> findAllByIboard(long iboard);

}
