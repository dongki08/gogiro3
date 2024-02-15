package com.green.gogiro.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="t_commu_pics")
public class CommunityPicsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long icommuPics;
    @ManyToOne
    @JoinColumn(name = "iboard", nullable = false)
    private CommunityEntity communityEntity;
    @Column(nullable = false)
    private String pic;
}
