package com.green.gogiro.entity.community;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="t_commu_pics")
public class CommunityPicsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long icommuPics;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iboard", nullable = false)
    private CommunityEntity communityEntity;
    @Column(nullable = false)
    private String pic;
}
