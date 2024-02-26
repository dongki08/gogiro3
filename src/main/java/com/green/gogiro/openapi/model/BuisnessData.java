package com.green.gogiro.openapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuisnessData {
    private String b_no;
    private String b_stt;
    private String b_stt_cd;

}
