package com.green.gogiro.butchershop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ButcherReportDto {
    private int ireview;

    @JsonIgnore
    private int iuser;

    private int ireport;
}
