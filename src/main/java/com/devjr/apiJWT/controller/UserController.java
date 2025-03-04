package com.devjr.apiJWT.controller;

import com.devjr.apiJWT.jwt.AuthRequest;
import com.devjr.apiJWT.jwt.AuthResponse;
import com.devjr.apiJWT.jwt.JwtUtil;
import com.devjr.apiJWT.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
        );

        UserDetails userDetails= (UserDetails) authentication.getPrincipal();

        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("USER");

        String jwt = jwtUtil.generateToken(authRequest.getUsername(),role);

        AuthResponse authResponse = new AuthResponse(authRequest.getUsername(), jwt);
        return ResponseEntity.ok(authResponse);
    }

    @GetMapping("/free")
    public String hello(){
        return "Hola mundo";
    }

    @GetMapping
    public String helloSecured(){
        return "hola mundo secured";
    }


}
