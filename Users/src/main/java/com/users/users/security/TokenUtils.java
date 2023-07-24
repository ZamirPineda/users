package com.users.users.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    private static final String ACCESS_TOKEN_SECRET = "ZXlKaFlYQWlPaUl4TWpBd01EQTNOeUlzSW10bGVWWmx";
    private static final Long ACCESS_VALIDITY_SECONDS = 2_592_000L;

    public static String createToken(String username, String dni) {
        long expirationTime = ACCESS_VALIDITY_SECONDS * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
        Map<String, Object> extra = new HashMap<>();
        extra.put("username", username);
        return Jwts.builder()
                .setSubject(dni)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }
    public static UsernamePasswordAuthenticationToken getAuth(String token){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String password = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(password, null, Collections.emptyList());
        }catch(JwtException e){
            return null;
        }
    }
}
