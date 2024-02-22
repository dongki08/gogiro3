package com.green.gogiro.entity.shop;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_shop_tag")
public class ShopTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long itag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ishop", nullable = false)
    private ShopEntity shopEntity;

    @Column(length = 10, nullable = false)
    private String tag;
}
