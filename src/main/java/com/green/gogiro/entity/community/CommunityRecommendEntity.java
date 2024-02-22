package com.green.gogiro.entity.community;


import com.green.gogiro.entity.BaseEntity;
import com.green.gogiro.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_commu_recommend")
public class CommunityRecommendEntity extends BaseEntity {
    @EmbeddedId
    private CommunityRecommendIds communityRecommendIds;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("iboard")
    @JoinColumn(name="iboard", columnDefinition="BIGINT UNSIGNED")
    private CommunityEntity communityEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("iuser")
    @JoinColumn(name="iuser", columnDefinition="BIGINT UNSIGNED")
    private UserEntity userEntity;
}
