package com.example.DigitalWalletBackend.security.jwt;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String JwtKey;
    @Value("${jwt.expiration}")
    private long  expirationTime;

    public String generateToken(String email,String role){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);
        return Jwts.builder()
                .setSubject(email)
                .claim("role",role)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,JwtKey)
                .compact();
    }

    public boolean isTokenValid(String token,String email){
        String userName = getUserNameFromToken(token);
        return (userName.equals(email) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token){
        Date expiration = Jwts.parser()
                .setSigningKey(JwtKey)
                .parseClaimsJws(token)
                .getBody().
                getExpiration();

        return expiration.before(new Date());
    }

    public String getUserNameFromToken(String token){
        return Jwts.parser()
                .setSigningKey(JwtKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getRoleFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(JwtKey)
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }
}
