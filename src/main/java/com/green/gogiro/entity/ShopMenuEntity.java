package com.green.gogiro.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_shop_menu")
public class ShopMenuEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long imenu;

    @ManyToOne
    @JoinColumn(name = "ishop", nullable = false)
    private ShopEntity ishop;

    @Column(length = 30, nullable = false)
    private String menu;

    @Column
    private int price;

    @Column(length = 50, nullable = false)
    private String pic;
}
