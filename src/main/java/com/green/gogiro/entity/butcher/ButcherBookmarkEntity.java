package com.green.gogiro.entity.butcher;

import com.green.gogiro.entity.CreatedAtEntity;
import com.green.gogiro.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_but_bookmark")
public class ButcherBookmarkEntity extends CreatedAtEntity {

    @EmbeddedId
    private ButcherBookmarkIds butcherBookmarkIds;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("iuser")
    @JoinColumn(name = "iuser", columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ibutcher")
    @JoinColumn(name = "ibutcher", columnDefinition = "BIGINT UNSIGNED")
    private ButcherEntity butcherEntity;

}
