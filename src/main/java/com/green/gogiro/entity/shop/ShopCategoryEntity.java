package com.green.gogiro.entity.shop;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_category")
public class ShopCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED", nullable = false)
    private Long imeat;

    @Column(length = 5, nullable = false)
    private String mtype;
}
