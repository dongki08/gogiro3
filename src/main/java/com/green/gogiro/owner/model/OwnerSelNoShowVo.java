package com.green.gogiro.owner.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OwnerSelNoShowVo {
    private Integer count;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<SelShopNoShowProcVo> ownerNoShowList = new ArrayList<>();
}
