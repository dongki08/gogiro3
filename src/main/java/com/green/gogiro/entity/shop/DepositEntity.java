package com.green.gogiro.entity.shop;

import com.green.gogiro.entity.CreatedAtEntity;
import com.green.gogiro.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_deposit")
public class DepositEntity extends CreatedAtEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ideposit;

    @ManyToOne
    @JoinColumn(name = "ishop")
    private ShopEntity shopEntity;

    @ManyToOne
    @JoinColumn(name = "iuser")
    private UserEntity userEntity;
}
