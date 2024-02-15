package com.green.gogiro.entity.butcher;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_but_review_pic")
public class ButcherReviewPicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ireview_pics", columnDefinition = "BIGINT UNSIGNED")
    private Long ireviewPics;

    @ManyToOne
    @JoinColumn(name = "ireview", nullable = false)
    private ButcherReviewEntity butcherReviewEntity;

    @Column(length = 2100, nullable = false)
    private String pic;


}
