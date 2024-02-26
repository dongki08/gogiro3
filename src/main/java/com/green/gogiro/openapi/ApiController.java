package com.green.gogiro.openapi;

import com.green.gogiro.openapi.model.BuisnessDto;
import com.green.gogiro.openapi.model.CompCodeVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApiController {
    private final CompCodeValidator service;

    @PostMapping("/api/status")
    @Operation(summary = "사업자상태조회",description = "사업자상태조회 처리")
    public CompCodeVo validate(@RequestBody BuisnessDto dto) throws Exception{
        return service.validate(dto);
    }
}
