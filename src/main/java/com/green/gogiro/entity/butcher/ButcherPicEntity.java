package com.green.gogiro.entity.butcher;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_butcher_pic")
public class ButcherPicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ibut_pics", columnDefinition = "BIGINT UNSIGNED")
    private Long ibutPics;

    @ManyToOne
    @JoinColumn(name = "ibutcher", nullable = false)
    private ButcherEntity butcherEntity;

    @Column(length = 2100, nullable = false)
    private String pic;
}
