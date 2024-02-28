package com.green.gogiro.owner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerNewReservationVo {
    private Long ireser;
    private Long iuser;
    private String name;
    private String date;
    private int headCount;
    private String request;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<SelButcherPickupMenuProcVo> pickupList = new ArrayList<>();
}
