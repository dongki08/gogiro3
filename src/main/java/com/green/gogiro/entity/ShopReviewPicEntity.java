package com.green.gogiro.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_shop_review_pic")
public class ShopReviewPicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ireview_pics", columnDefinition = "BIGINT UNSIGNED")
    private Long ireviewPics;

    @ManyToOne
    @JoinColumn(name = "ireview", nullable = false)
    private ShopReviewEntity ireview;

    @Column(length = 50, nullable = false)
    private String pic;
}
