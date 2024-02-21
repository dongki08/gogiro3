package com.green.gogiro.owner.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewQdslRepository {
    List<OwnerReviewVo> selByReviewAll(long ishop, int checkShop, Pageable pageable);
}
