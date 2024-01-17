package com.project.userservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.userservice.security.JwtTokenUtil;
import com.project.userservice.users.data.user;
import com.project.userservice.users.services.userRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.http.HttpHeaders;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private userRepo userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authorizationHeader.split(" ")[1].trim();
        if (!jwtTokenUtil.validateToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String userEmail = jwtTokenUtil.getUsernameFromToken(token);
        user loggedInUser = userRepository.findByEmail(userEmail);

        if (!loggedInUser.getActivated()) {
            filterChain.doFilter(request, response);
            return;
        }

        if (loggedInUser.getBan_till()!=null && loggedInUser.getBan_till().isAfter(LocalDateTime.now())) {
            filterChain.doFilter(request, response);

            return;
        }

        String sessionIdFromHeader = request.getHeader("session_id");
        if (sessionIdFromHeader == null || !sessionIdFromHeader.equals(loggedInUser.getUser_activeSession())) {
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userEmail, null, List.of(new SimpleGrantedAuthority("ROLE_" + loggedInUser.getUser_role())));
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }



}
