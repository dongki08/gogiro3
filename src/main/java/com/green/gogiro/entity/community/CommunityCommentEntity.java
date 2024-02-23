package com.green.gogiro.entity.community;

import com.green.gogiro.entity.BaseEntity;
import com.green.gogiro.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="t_community_comment")
public class CommunityCommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long icomment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iboard", nullable = false)
    private CommunityEntity communityEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iuser", nullable = false)
    private UserEntity userEntity;

    @Column(length = 50,nullable = false)
    private String contents;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int count;

    @ToString.Exclude
    @OneToMany(mappedBy = "communityCommentEntity", cascade = CascadeType.PERSIST)
    private List<CommunityCommentCountEntity> communityCommentCountEntityList = new ArrayList<>();
}
