package com.green.gogiro.openapi.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Businesses {
    // - 필히 제거.
    // 사업자 등록 번호
    private List<String> b_no;

}
