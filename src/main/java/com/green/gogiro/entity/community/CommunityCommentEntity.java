package com.green.gogiro.entity.community;

import com.green.gogiro.entity.BaseEntity;
import com.green.gogiro.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="t_community_comment")
public class CommunityCommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long icomment;
    @ManyToOne
    @JoinColumn(name = "iboard", nullable = false)
    private CommunityEntity communityEntity;
    @ManyToOne
    @JoinColumn(name = "iuser", nullable = false)
    private UserEntity userEntity;
    @Column(length = 50,nullable = false)
    private String contents;
}
