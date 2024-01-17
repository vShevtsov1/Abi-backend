package com.project.developersservice.security;


import com.project.developersservice.feigns.UserFeign;
import com.project.userservice.users.data.user;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserFeign userFeign;

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
        user loggedInUser = userFeign.getUserByEmail(userEmail);

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
