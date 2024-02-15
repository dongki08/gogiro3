package com.green.gogiro.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "t_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long iuser;

    @Column(length = 20)
    private String email;
}
