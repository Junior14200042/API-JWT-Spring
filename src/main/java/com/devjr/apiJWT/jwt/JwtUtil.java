package com.devjr.apiJWT.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {


    @Value("${configuration.secret-key}")
    private String SECRET_KEY;

    @Value("${date.expiration}")
    private Long FECHA_EXPIRATION;

    public Key getKey(){
        return new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    public String generateToken(String username,String role){

        return Jwts.builder()
                .setSubject(username)
                .claim("role",role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+FECHA_EXPIRATION))
                .signWith(getKey())
                .compact();

    }

    public String extractUsername(String token){

        JwtParser parser= Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build();

        Claims claims = parser.parseClaimsJws(token).getBody();

        return claims.getSubject();

    }

    public boolean tokenIsExpired(String token){
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build();

        Claims claims = parser.parseClaimsJws(token).getBody();

        return claims.getExpiration()
                .before(new Date());
    }

    public boolean validateToken(String token, String username){
        return username.equals(extractUsername(token)) && !tokenIsExpired(token);
    }


}
