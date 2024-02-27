package com.green.gogiro.owner.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerSelReservationVo {
    private int checkShop;
    private int count;
    List<OwnerNewReservationVo> ownerReservationList = new ArrayList<>();

}
