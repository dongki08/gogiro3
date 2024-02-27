package com.green.gogiro.entity.butcher;

import com.green.gogiro.entity.BaseEntity;
import com.green.gogiro.entity.UserEntity;
import com.green.gogiro.entity.shop.ShopPicEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "t_butcher_shop")
public class ButcherEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long ibutcher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iuser", nullable = false)
    private UserEntity userEntity;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String location;

    @Column(length = 40, nullable = false)
    private String x;

    @Column(length = 40, nullable = false)
    private String y;

    @Column(length = 200)
    private String open;

    @Column(length = 15)
    private String tel;

    @Column(length = 10,nullable = false, unique = true)
    private String number;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int confirm; // 0:대기 1:승인 2:거절(퇴출)

    @ToString.Exclude
    @OneToMany(mappedBy = "butcherEntity",cascade = CascadeType.PERSIST)
    private List<ButcherPicEntity> butcherPicEntityList = new ArrayList<>();

}
