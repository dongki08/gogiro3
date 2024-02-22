package com.green.gogiro.entity;

import com.green.gogiro.entity.butcher.ButcherMenuEntity;
import com.green.gogiro.entity.butcher.PickupEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table(name = "t_pickup_menu")
public class PickupMenuEntity extends BaseEntity{

    @EmbeddedId
    private PickupMenuIds pickupMenuIds;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ipickup")
    @JoinColumn(name = "ipickup", columnDefinition = "BIGINT UNSIGNED")
    private PickupEntity ipickup;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ibutMenu")
    @JoinColumn(name = "ibut_menu", columnDefinition = "BIGINT UNSIGNED")
    private ButcherMenuEntity ibutMenu;

    @Column(name = "count", nullable = false)
    @ColumnDefault("1")
    private int count;
}
