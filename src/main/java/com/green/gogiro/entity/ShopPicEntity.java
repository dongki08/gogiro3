package com.green.gogiro.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_shop_pic")
public class ShopPicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ishop_pics", columnDefinition = "BIGINT UNSIGNED")
    private Long ishopPics;

    @ManyToOne
    @JoinColumn(name = "ishop", nullable = false)
    private ShopEntity ishop;

    @Column(length = 50, nullable = false)
    private String pic;
}
