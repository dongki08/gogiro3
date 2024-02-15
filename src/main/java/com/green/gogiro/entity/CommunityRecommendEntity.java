package com.green.gogiro.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_commu_recommend")
public class CommunityRecommendEntity extends BaseEntity{
    @EmbeddedId
    private CommunityRecommendIds communityRecommendIds;
    @ManyToOne
    @MapsId("iboard")
    @JoinColumn(name="iboard", columnDefinition="BIGINT UNSIGNED")
    private CommunityEntity communityEntity;
    @ManyToOne
    @MapsId("iuser")
    @JoinColumn(name="iuser", columnDefinition="BIGINT UNSIGNED")
    private UserEntity userEntity;
}
