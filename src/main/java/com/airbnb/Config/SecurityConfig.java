package com.airbnb.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Disable CSRF and CORS
        http.csrf().disable().cors().disable();
        //haap
        http.authorizeHttpRequests().anyRequest().permitAll();

//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//        http.authorizeHttpRequests().requestMatchers("/api/v1/auth/createuser",
//                        "/api/v1/auth/createpropertyowner", "/api/v1/auth/login")
//                .permitAll()
//                .requestMatchers("/api/v1/addProperty")
//                .hasAnyRole("QWNER", "ADMIN", "MANAGER")
//                .requestMatchers("/api/v1/auth/createpropertymanager")
//                .hasRole("OWNER")
//                .anyRequest()
//                .authenticated();
        return http.build();
    }
}

