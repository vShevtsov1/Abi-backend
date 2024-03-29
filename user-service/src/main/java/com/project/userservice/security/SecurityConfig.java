package com.project.userservice.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    private JwtTokenFilter JwtTokenFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())


                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/feign-get-user").permitAll()
                        .requestMatchers("/users/new-user").permitAll()
                        .requestMatchers("/users/login-user").permitAll()
                        .requestMatchers("/users/set-avatar").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(JwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, authException)
                                -> response.sendError(
                                HttpServletResponse.SC_UNAUTHORIZED,
                                authException.getLocalizedMessage()
                        )
                );

        return http.build();
    }



}