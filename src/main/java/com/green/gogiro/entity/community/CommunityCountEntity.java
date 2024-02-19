package com.green.gogiro.entity.community;

import com.green.gogiro.entity.CreatedAtEntity;
import com.green.gogiro.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_community_count")
public class CommunityCountEntity extends CreatedAtEntity {

    @EmbeddedId
    private CommunityCountIds communityCountIds;

    @ManyToOne
    @MapsId("iuser")
    @JoinColumn(name = "iuser", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userEntity;

    @ManyToOne
    @MapsId("iboard")
    @JoinColumn(name = "iboard", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private CommunityEntity communityEntity;


}
