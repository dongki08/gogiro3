package com.green.gogiro.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table(name = "t_black_list_count")
public class BlackListEntity extends BaseEntity{

    @Id
    @OneToOne
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "iuser", nullable = false)
    private UserEntity iuser;

    @Column(name = "count", nullable = false)
    @ColumnDefault("0")
    private int count;

    @Column(name = "reservation_count", nullable = false)
    @ColumnDefault("0")
    private int reservationCount;
}
