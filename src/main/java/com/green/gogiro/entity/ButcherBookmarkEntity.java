package com.green.gogiro.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.checkerframework.checker.units.qual.C;

@Data
@Entity
@Table(name = "t_but_bookmark")
public class ButcherBookmarkEntity extends CreatedAtEntity {

    @EmbeddedId
    private ButcherBookmarkIds butcherBookmarkIds;

    @ManyToOne
    @MapsId("iuser")
    @JoinColumn(name = "iuser", columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userEntity;

    @ManyToOne
    @MapsId("ibutcher")
    @JoinColumn(name = "ibutcher", columnDefinition = "BIGINT UNSIGNED")
    private ButcherEntity butcherEntity;

}
