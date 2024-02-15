package com.green.gogiro.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_butcher_tag")
public class ButcherTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long itag;

    @ManyToOne
    @JoinColumn(name = "ibutcher", nullable = false)
    private ButcherEntity butcherEntity;

    @Column(length = 10, nullable = false)
    private String tag;

}
