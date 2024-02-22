package com.green.gogiro.entity.butcher;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Embeddable
@EqualsAndHashCode
public class PickupMenuIds implements Serializable {
    private Long ipickup;
    private Long ibutMenu;
}
