package com.green.gogiro.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KakaoService {

    private final ObjectMapper objectMapper;

    public Integer getAxis(String query) {

        String kakaoAxis = getKakaoAxis(query);
        String[] result = kakaoAxis.split(", ");
        List<String> results = new ArrayList<>();
        for (String string : result) {

            results.add(string.substring(string.indexOf(":") + 1));
        }

        results.forEach(System.out::println);

        return 1;
    }

    private String getKakaoAxis(String query) {
        System.out.println(query);
//        대구시 북구 침산로21길 10
        if (query.contains("%")) {
            query = query.replaceAll("%", " ");
        }
        String queryPram = "?query=" + query;

        RestClient restClient = RestClient.builder()
                .baseUrl("https://dapi.kakao.com/v2/local/search/address")
                .build();

        String result = restClient.get()
                .uri(queryPram)
                .header("Authorization", "KakaoAK 3e699a92a632b4cb5801e78215a649a5")
                .retrieve()
                .body(String.class);

        System.out.println(result);

        Documents documents;
        try {
            documents = objectMapper.readValue(result, Documents.class);
        } catch (
                JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        StringBuilder sb = new StringBuilder();

        documents.getDocuments().forEach(addr -> {
            sb.append("x:").append(addr.getX()).append(", y:").append(addr.getY());
        });

        return sb.toString();
    }
}
