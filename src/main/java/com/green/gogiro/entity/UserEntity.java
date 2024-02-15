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

    @Column(length = 20,unique = true,nullable = false)
    private String email;

    @Column(length = 300,nullable = false)
    private String upw;

    private String name;

    private String nickname;

    private String birth;

    private String gender;

    private String address;

    private String pic;






}
