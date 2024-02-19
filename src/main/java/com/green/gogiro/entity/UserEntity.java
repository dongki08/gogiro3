package com.green.gogiro.entity;

import com.green.gogiro.common.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;


@Data
@Entity
@Table(name = "t_user")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long iuser;

    @Column(length = 20, unique = true, nullable = false)
    private String email;

    @Column(length = 300, nullable = false)
    private String upw;

    @Column(length = 10)
    private String name;

    @Column(length = 10)
    private String nickname;

    @Column(length = 10)
    private String birth;

    @Column(length = 10)
    private String gender;

    @Column(length = 10)
    private String address;

    @Column(length = 100)
    private String pic;

    @Column(length = 15)
    private String tel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("'USER'")
    private RoleEnum role;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int check_shop;
}
