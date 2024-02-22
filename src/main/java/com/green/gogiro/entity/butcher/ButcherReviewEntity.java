package com.green.gogiro.entity.butcher;

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
@Table(name = "t_but_review")
public class ButcherReviewEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ireview", columnDefinition = "BIGINT UNSIGNED")
    private Long ireview;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_butcher", nullable = false)
    private ButcherEntity butcherEntity;

    @ManyToOne(fetch = FetchType.LAZY)
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
    @OneToMany(mappedBy = "butcherReviewEntity",cascade = CascadeType.PERSIST)
    private List<ButcherReviewPicEntity> butcherReviewPicEntityList = new ArrayList<>();
}
