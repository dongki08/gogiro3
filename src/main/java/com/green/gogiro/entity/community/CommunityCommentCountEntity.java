package com.green.gogiro.entity.community;


import com.green.gogiro.entity.CreatedAtEntity;
import com.green.gogiro.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_comment_count")
public class CommunityCommentCountEntity extends CreatedAtEntity {

    @EmbeddedId
    private CommunityCountIds communityCountIds;

    @ManyToOne
    @MapsId("icomment")
    @JoinColumn(name = "icomment",nullable = false,columnDefinition = "BIGINT UNSIGNED")
    private CommunityEntity communityEntity;

    @ManyToOne
    @MapsId("iuser")
    @JoinColumn(name = "iuser",nullable = false,columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userEntity;
}
