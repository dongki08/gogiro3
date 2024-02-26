package com.green.gogiro.openapi;

import com.green.gogiro.common.PubDataApiRequester;
import com.green.gogiro.openapi.model.BuisnessDto;
import com.green.gogiro.openapi.model.BuisnessVo;
import com.green.gogiro.openapi.model.Businesses;
import com.green.gogiro.openapi.model.CompCodeVo;
import com.green.gogiro.openapi.properties.CompCodeProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class CompCodeValidator {

    private final PubDataApiRequester apiRequester;
    private final CompCodeProperties compCodeProperties;

    public CompCodeVo validate(BuisnessDto dto) {

        Businesses businesses = Businesses.builder()
                .b_no(dto.getB_no())
                .build();
        String subUri = "?" + compCodeProperties.getServiceCodeKey() + "=" + compCodeProperties.getServiceCodeValue();
        BuisnessVo result = apiRequester.post(compCodeProperties.getBaseUrl(), subUri, businesses, BuisnessVo.class);
        log.info("result = {}", result);
        try {

            if (result.getMatch_cnt() != 0) {

                return CompCodeVo.builder()
                        .statusCode(result.getStatus_code())
                        .matchCnt(result.getMatch_cnt())
                        .compCode(result.getData().get(0).getB_no())
                        .compStt(result.getData().get(0).getB_stt())
                        .build();
            }
        } catch (NullPointerException e) {
            return CompCodeVo.builder()
                    .statusCode("FAIL")
                    .compStt("존재 하지 않는 사업자 입니다")
                    .build();
        }
        return null;
    }

}
