package com.green.gogiro.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_reservation")
public class ShopReservationEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long ireser;

    @ManyToOne
    @JoinColumn(name = "iuser", nullable = false)
    private UserEntity iuser;

    @ManyToOne
    @JoinColumn(name = "ishop", nullable = false)
    private ShopEntity ishop;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(length = 50)
    private String request;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int confirm;

    @Column(name = "head_count", nullable = false)
    private int headCount;

}
