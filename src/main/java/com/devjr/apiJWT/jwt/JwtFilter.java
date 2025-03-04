package com.devjr.apiJWT.jwt;

import com.devjr.apiJWT.services.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserServiceImpl userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        String username=null;
        String token=null;

        if(authorizationHeader!= null && authorizationHeader.startsWith("Bearer ")){
            token = authorizationHeader.substring(7);
            username= jwtUtil.extractUsername(token);
        }

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = userService.loadUserByUsername(username);

            if(jwtUtil.validateToken(token,userDetails.getUsername())){
                UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(),"",userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request,response);

    }
}
