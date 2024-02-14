package com.green.gogiro.kakao;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class KakaoController {



    private final KakaoService service;

    @GetMapping("/kakao")
    public Integer map(String query) {
        return service.getAxis(query);
    }

}
