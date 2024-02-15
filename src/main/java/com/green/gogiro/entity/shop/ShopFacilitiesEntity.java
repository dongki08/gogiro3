package com.green.gogiro.entity.shop;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_shop_facilities")
public class ShopFacilitiesEntity {

    @EmbeddedId
    private FacilityIds facilityIds;

    @ManyToOne
    @MapsId("ishop")
    @JoinColumn(name = "ishop",columnDefinition = "BIGINT UNSIGNED")
    private ShopEntity shopEntity;

    @ManyToOne
    @MapsId("ifacil")
    @JoinColumn(name = "ifacil",columnDefinition = "BIGINT UNSIGNED")
    private FacilityEntity facilityEntity;
}
