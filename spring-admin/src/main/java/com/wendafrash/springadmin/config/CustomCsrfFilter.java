package com.wendafrash.springadmin.config;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CustomCsrfFilter extends OncePerRequestFilter {

    private CsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        CsrfToken csrf = csrfTokenRepository.loadToken(request);
        if (csrf != null) {
            // Add CSRF token to the response header or cookie as per your requirement
            response.setHeader("X-CSRF-TOKEN", csrf.getToken());
        }
        filterChain.doFilter(request, response);
    }
}
