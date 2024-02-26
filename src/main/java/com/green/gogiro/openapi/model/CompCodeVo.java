package com.green.gogiro.openapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompCodeVo {
    private String statusCode;
    private Integer matchCnt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String compCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String compStt;

}
