package com.kolev.stock.app.myapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers("/api/users/login").permitAll()
                .requestMatchers("/api/users/register").permitAll()
                .requestMatchers("/login*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().permitAll();

        return http.build();
    }
}