package com.green.gogiro.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_pickup")
public class PickupEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ipickup", columnDefinition = "BIGINT UNSIGNED")
    private Long ipickup;

    @ManyToOne
    @JoinColumn(name = "iuser", nullable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "ibutcher", nullable = false)
    private ButcherEntity butcherEntity;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(length = 50)
    private String request;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int confirm;

}
