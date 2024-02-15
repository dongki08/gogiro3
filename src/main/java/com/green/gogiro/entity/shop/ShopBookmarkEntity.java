package com.green.gogiro.entity.shop;

import com.green.gogiro.entity.UserEntity;
import com.green.gogiro.entity.CreatedAtEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_shop_bookmark")
public class ShopBookmarkEntity extends CreatedAtEntity {

    @EmbeddedId
    private ShopBookmarkIds bookmarkIds;

    @OneToOne
    @MapsId("iuser")
    @JoinColumn(name = "iuser", columnDefinition = "BIGINT UNSIGNED")
    private UserEntity iuser;

    @OneToOne
    @MapsId("ishop")
    @JoinColumn(name = "ishop", columnDefinition = "BIGINT UNSIGNED")
    private ShopEntity ishop;
}
