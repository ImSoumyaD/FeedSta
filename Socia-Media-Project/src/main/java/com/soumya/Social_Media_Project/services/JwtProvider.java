package com.soumya.Social_Media_Project.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Date;

public class JwtProvider {

    private static final Key KEY = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth){
        String token = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * (60 * 60) * 24) ) // 24hr
                .claim("email",auth.getName())
                .signWith(KEY)
                .compact();

        return token;
    }

    public static String getEmailFromToken(String jwt) throws JwtException {
        try {
            //Bearer token
            String token = jwt.substring(7);
            Claims claims = Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody();
            String email = String.valueOf(claims.get("email"));
            return email;
        }catch (ExpiredJwtException e){
            throw new JwtException("Token Expired...");
        }catch (MalformedJwtException e){
            throw new JwtException("Token Malformed...");
        }catch (Exception e){
            throw new JwtException("Invalid token...");
        }
    }

}
