package com.green.gogiro.owner.model;

import lombok.Data;

@Data
public class OwnerNewReservationVo {
    private Long ireser;
    private Long iuser;
    private String name;
    private String date;
    private int headCount;
    private String request;
}
