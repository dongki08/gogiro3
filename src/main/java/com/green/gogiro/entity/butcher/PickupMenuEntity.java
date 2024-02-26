package com.green.gogiro.entity.butcher;

import com.green.gogiro.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_pickup_menu")
public class PickupMenuEntity extends BaseEntity {
    @EmbeddedId
    private PickupMenuIds pickupMenuIds;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ipickup")
    @JoinColumn(name = "ipickup", nullable = false)
    private PickupEntity pickupEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ibutMenu")
    @JoinColumn(name = "ibut_menu", nullable = false)
    private ButcherMenuEntity butcherMenuEntity;

    @Column(name = "count", nullable = false)
    @ColumnDefault("1")
    private int count;
}
