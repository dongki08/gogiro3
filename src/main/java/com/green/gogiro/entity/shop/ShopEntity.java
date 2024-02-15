package com.green.gogiro.entity.shop;

import com.green.gogiro.entity.BaseEntity;
import com.green.gogiro.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_shop")
public class ShopEntity extends BaseEntity {

    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ishop;

    @ManyToOne
    @JoinColumn(name = "iuser", nullable = false)
    private UserEntity iuser;

    @ManyToOne
    @JoinColumn(name = "imeat", nullable = false)
    private ShopCategoryEntity imeat;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(length = 20, nullable = false)
    private String x;

    @Column(length = 20, nullable = false)
    private String y;

    @Column(length = 50)
    private String open;

    @Column(length = 15)
    private String tel;
}
