package com.green.gogiro.reservation.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PickupMenuDto {
    @JsonIgnore
    private int ipickup;
    @Schema(title = "메뉴pk")
    @Min(value = 1)
    private int ibutMenu;
    @Schema(title = "수량")
    @Min(value = 1)
    private int count;
}
