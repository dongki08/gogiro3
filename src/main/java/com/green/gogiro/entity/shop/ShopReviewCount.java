package com.green.gogiro.entity.shop;

import com.green.gogiro.entity.CreatedAtEntity;
import com.green.gogiro.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_shop_review_count")
public class ShopReviewCount extends CreatedAtEntity {
    @EmbeddedId
    private ShopReviewCountIds shopReviewCountIds;

    @ManyToOne
    @MapsId("ireview")
    @JoinColumn(name = "ireview", columnDefinition = "BIGINT UNSIGNED", nullable = false)
    private ShopReviewEntity shopReviewEntity;

    @ManyToOne
    @MapsId("iuser")
    @JoinColumn(name = "iuser", columnDefinition = "BIGINT UNSIGNED", nullable = false)
    private UserEntity iuser;

}
