package com.green.gogiro.entity.community;


import com.green.gogiro.entity.CreatedAtEntity;
import com.green.gogiro.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_comment_fav")
public class CommunityFavEntity extends CreatedAtEntity {

    @EmbeddedId
    private CommunityFavIds communityFavIds;


    @ManyToOne
    @MapsId("iuser")
    @JoinColumn(name = "iuser",columnDefinition = "BIGINT UNSIGNED",nullable = false)
    private UserEntity userEntity;


    @ManyToOne
    @MapsId("iboard")
    @JoinColumn(name = "iboard",columnDefinition = "BIGINT UNSIGNED",nullable = false)
    private CommunityEntity communityEntity;


}
