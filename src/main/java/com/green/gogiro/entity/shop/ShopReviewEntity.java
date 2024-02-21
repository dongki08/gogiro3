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
@Table(name = "t_shop_review")
public class ShopReviewEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long ireview;

    @ManyToOne
    @JoinColumn(name = "ishop", nullable = false)
    private ShopEntity shopEntity;

    @ManyToOne
    @JoinColumn(name = "iuser", nullable = false)
    private UserEntity userEntity;

    @Column(nullable = false)
    @ColumnDefault("1")
    private int star;

    @Column(length = 50, nullable = false)
    private String review;

    @Column(length = 30)
    private String comment;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int count;

    @ToString.Exclude
    @OneToMany(mappedBy = "shopReviewEntity",cascade = CascadeType.PERSIST)
    private List<ShopReviewPicEntity> shopReviewPicEntityList = new ArrayList<>();
}
