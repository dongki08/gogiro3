package com.green.gogiro.entity.butcher;

import com.green.gogiro.entity.CreatedAtEntity;
import com.green.gogiro.entity.UserEntity;
import com.green.gogiro.entity.community.CommunityRecommendIds;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_but_review_count")
public class ButcherReviewCount extends CreatedAtEntity {
    @EmbeddedId
    private ButcherReviewCountIds butcherReviewCountIds;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ireview")
    @JoinColumn(name = "ireview", columnDefinition = "BIGINT UNSIGNED", nullable = false)
    private ButcherReviewEntity butcherReviewEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("iuser")
    @JoinColumn(name = "iuser", columnDefinition = "BIGINT UNSIGNED", nullable = false)
    private UserEntity iuser;
}
