package com.green.gogiro.entity.butcher.repository;

import com.green.gogiro.entity.butcher.ButcherBookmarkEntity;
import com.green.gogiro.entity.butcher.ButcherBookmarkIds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ButcherBookmarkRepository extends JpaRepository<ButcherBookmarkEntity, ButcherBookmarkIds> {
}
