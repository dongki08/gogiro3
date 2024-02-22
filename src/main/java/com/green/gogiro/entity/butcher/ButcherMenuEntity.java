package com.green.gogiro.entity.butcher;

import com.green.gogiro.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_but_menu")
public class ButcherMenuEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ibut_menu", columnDefinition = "BIGINT UNSIGNED")
    private Long ibutMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ibutcher", nullable = false)
    private ButcherEntity butcherEntity;

    @Column(length = 30, nullable = false)
    private String menu;

    @Column(columnDefinition = "BIGINT UNSIGNED")
    private int price;

    @Column(length = 2100)
    private String pic;

}
