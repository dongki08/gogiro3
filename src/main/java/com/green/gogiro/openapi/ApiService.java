package com.green.gogiro.openapi;

import com.green.gogiro.openapi.model.BuisnessDto;
import com.green.gogiro.openapi.model.CompCodeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiService {
    private final CompCodeValidator compCodeValidator;

    public CompCodeVo validate(BuisnessDto dto) throws Exception{
        return compCodeValidator.validate(dto);
    }
}
