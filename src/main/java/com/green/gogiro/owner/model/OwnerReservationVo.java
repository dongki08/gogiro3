package com.green.gogiro.owner.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OwnerReservationVo {
    List<OwnerNewReservationVo> ownerReservationList = new ArrayList<>();
    List<OwnerNoShowVo> ownerNoShowList = new ArrayList<>();
}
