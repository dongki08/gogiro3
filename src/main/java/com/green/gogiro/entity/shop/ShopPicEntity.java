package com.green.gogiro.entity.shop;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "t_shop_pic")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopPicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ishop_pics", columnDefinition = "BIGINT UNSIGNED")
    private Long ishopPics;

    @ManyToOne
    @JoinColumn(name = "ishop", nullable = false)
    private ShopEntity shopEntity;

    @Column(length = 50, nullable = false)
    private String pic;
}
