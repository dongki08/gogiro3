package com.green.gogiro.entity.community;

import com.green.gogiro.entity.BaseEntity;
import com.green.gogiro.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Checks;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DialectOverride;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "t_community")
public class CommunityEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long iboard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iuser", nullable = false)
    private UserEntity userEntity;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(length = 300, nullable = false)
    private String contents;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int count;

    @Column(nullable = false)
    @ColumnDefault("0")
    @Check(constraints = "announce in(0,1)")
    private int announce;

    @ToString.Exclude
    @OneToMany(mappedBy = "communityEntity", cascade = CascadeType.PERSIST)
    private List<CommunityPicsEntity> communityPicsEntityList = new ArrayList<>();
}
