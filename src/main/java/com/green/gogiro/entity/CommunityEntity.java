package com.green.gogiro.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_community")
public class CommunityEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long iboard;
    @ManyToOne
    @JoinColumn(name = "iuser", nullable = false)
    private UserEntity userEntity;
    @Column(length = 30, nullable = false)
    private String title;
    @Column(length = 300, nullable = false)
    private String contents;
}
