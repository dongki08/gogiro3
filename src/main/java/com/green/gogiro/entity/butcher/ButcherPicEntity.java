package com.green.gogiro.entity.butcher;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "t_butcher_pic")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ButcherPicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ibut_pics", columnDefinition = "BIGINT UNSIGNED")
    private Long ibutPics;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ibutcher", nullable = false)
    private ButcherEntity butcherEntity;

    @Column(length = 2100, nullable = false)
    private String pic;
}
