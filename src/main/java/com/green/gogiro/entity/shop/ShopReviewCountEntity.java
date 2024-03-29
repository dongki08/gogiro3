package com.green.gogiro.entity.shop;

import com.green.gogiro.entity.CreatedAtEntity;
import com.green.gogiro.entity.UserEntity;
import com.green.gogiro.entity.community.ReportEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_shop_review_count")
public class ShopReviewCountEntity extends CreatedAtEntity {
    @EmbeddedId
    private ShopReviewCountIds shopReviewCountIds;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ireview")
    @JoinColumn(name = "ireview", columnDefinition = "BIGINT UNSIGNED", nullable = false)
    private ShopReviewEntity shopReviewEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("iuser")
    @JoinColumn(name = "iuser", columnDefinition = "BIGINT UNSIGNED", nullable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ireport", nullable = false)
    private ReportEntity ireport;
}
