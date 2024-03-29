package com.green.gogiro;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableJpaAuditing
public class GogiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(GogiroApplication.class, args);
    }

    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customizer() {
        return p -> p.setOneIndexedParameters(true);
    }
}
