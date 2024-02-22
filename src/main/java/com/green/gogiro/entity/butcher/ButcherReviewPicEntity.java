package com.green.gogiro.entity.butcher;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_but_review_pic")
public class ButcherReviewPicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ireview_pics", columnDefinition = "BIGINT UNSIGNED")
    private Long ireviewPics;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ireview", nullable = false)
    private ButcherReviewEntity butcherReviewEntity;

    @Column(length = 2100, nullable = false)
    private String pic;


}
