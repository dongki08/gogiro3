package com.green.gogiro.entity;

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

    @ManyToOne
    @JoinColumn(name = "ishop", nullable = false)
    private ShopEntity shop;

    @Column(length = 10, nullable = false)
    private String tag;
}
