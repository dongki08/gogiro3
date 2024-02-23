package com.green.gogiro.entity.shop;

import com.green.gogiro.entity.BaseEntity;
import com.green.gogiro.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "t_shop")
public class ShopEntity extends BaseEntity {

    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ishop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iuser", nullable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imeat", nullable = false)
    private ShopCategoryEntity shopCategoryEntity;

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

    @Column(length = 20,nullable = false, unique = true)
    private String number;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int confirm; // 0:대기 1:승인 2:거절(퇴출)

    @Column
    @ColumnDefault("0")
    private int deposit;

    @ToString.Exclude
    @OneToMany(mappedBy = "shopEntity",cascade = CascadeType.PERSIST)
    private List<ShopPicEntity> shopPicEntityList = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "shopEntity",cascade = CascadeType.PERSIST)
    private List<ShopFacilitiesEntity> shopFacilitiesEntityList = new ArrayList<>();
}
