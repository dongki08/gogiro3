package com.green.gogiro.entity.butcher;


import com.green.gogiro.entity.BaseEntity;
import com.green.gogiro.entity.CreatedAtEntity;
import com.green.gogiro.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "t_payment")
public class ButcherPaymentEntity extends CreatedAtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long ipayment;

    @ManyToOne
    @JoinColumn(name = "ibutcher")
    private ButcherEntity butcherEntity;

    @ManyToOne
    @JoinColumn(name = "iuser")
    private UserEntity userEntity;

    @Column(nullable = false)
    private int total;


}
