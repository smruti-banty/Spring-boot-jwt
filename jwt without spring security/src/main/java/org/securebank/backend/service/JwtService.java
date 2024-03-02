package org.securebank.backend.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;

@Service
public class JwtService {
    private static final String secret = "fVoiShy5t3rep+mLD229aQjJi+vTOTSiL481w+YHzcc=";

    public String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String validToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey()).build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    private Key getKey() {
        byte[] key = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(key);
    }
}
