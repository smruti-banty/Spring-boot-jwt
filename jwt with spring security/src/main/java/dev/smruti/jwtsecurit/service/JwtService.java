package dev.smruti.jwtsecurit.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;

@Service
public class JwtService {
    private static String secret = "oli6qo0hZ5vip75VYJbTHzwkNu04QBH79s9ePfkV1u0=";
    public String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateToken(String token) {
        var claim = Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
        return claim.getBody().getSubject();
    }

    private Key getKey() {
        var bytes = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(bytes);
    }
}
