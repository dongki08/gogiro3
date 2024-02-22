package com.green.gogiro.entity.community;


import com.green.gogiro.entity.CreatedAtEntity;
import com.green.gogiro.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_community_fav")
public class CommunityFavEntity extends CreatedAtEntity {

    @EmbeddedId
    private CommunityFavIds communityFavIds;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("iuser")
    @JoinColumn(name = "iuser",columnDefinition = "BIGINT UNSIGNED",nullable = false)
    private UserEntity userEntity;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("iboard")
    @JoinColumn(name = "iboard",columnDefinition = "BIGINT UNSIGNED",nullable = false)
    private CommunityEntity communityEntity;


}
