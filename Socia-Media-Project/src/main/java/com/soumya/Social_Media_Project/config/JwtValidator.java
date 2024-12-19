package com.soumya.Social_Media_Project.config;

import com.soumya.Social_Media_Project.services.JwtConstant;
import com.soumya.Social_Media_Project.services.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtValidator extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(JwtConstant.JWT_HEADER); // Authorization

        if (jwt != null){
            try {
                String email = JwtProvider.getEmailFromToken(jwt);

                List<GrantedAuthority> authorities = new ArrayList<>(); // no roles needed to empty list

                Authentication authentication = new UsernamePasswordAuthenticationToken(email,null,authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);

            }catch (Exception e){
                throw new BadCredentialsException(e.getMessage());
            }
        }

        filterChain.doFilter(request,response);
    }
}
