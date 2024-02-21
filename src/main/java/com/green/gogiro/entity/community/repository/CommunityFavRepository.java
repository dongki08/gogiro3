package com.green.gogiro.entity.community.repository;

import com.green.gogiro.entity.community.CommunityFavEntity;
import com.green.gogiro.entity.community.CommunityFavIds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityFavRepository extends JpaRepository<CommunityFavEntity, CommunityFavIds> {
}
