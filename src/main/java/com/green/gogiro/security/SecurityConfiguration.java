package com.green.gogiro.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(http -> http.disable())
                .formLogin(form -> form.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                                                                    "/api/user/signin"
                                                                    , "/api/user/signup"
                                                                    , "/api/user/signup/**"
                                                                    , "/login"
                                                                    , "/join"
                                                                    , "/join/**"
                                                                    , "/error"
                                                                    , "/err"
                                                                    , "/"
                                                                    , "/static"
                                                                    , "/static/**"
                                                                    , "/asset-manifest.json"
                                                                    , "/pic"
                                                                    , "/pic/**"
                                                                    , "/main"
                                                                    , "/assets"
                                                                    , "/assets/**"
                                                                    , "/assets/aboutimages/**"
                                                                    , "/json"
                                                                    , "/json/**"
                                                                    , "/meat"
                                                                    , "/meat/**"
                                                                    , "/butcher"
                                                                    , "/butcher/**"
                                                                    , "/my"
                                                                    , "/my/**"
                                                                    , "/community"
                                                                    , "/community/list"
                                                                    , "/community/read"
                                                                    , "/community/read/**"
                                                                    , "/index.html"
                                                                    , "/swagger.html"
                                                                    , "/swagger-ui/**"
                                                                    , "/v3/api-docs/**"
                                                                    , "/api/user/refresh-token"
                                )
                        .permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/butcher-shop","/api/butcher-shop/**"
                                                                , "/api/shop", "/api/shop/**"
                                                                , "/api/community"
                                                                , "/api/community/**")
                                .permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(execpt -> {
                    execpt.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                            .accessDeniedHandler(new JwtAccessDeniedHandler());
                })
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
