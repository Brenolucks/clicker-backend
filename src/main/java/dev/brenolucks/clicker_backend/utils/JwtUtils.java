package dev.brenolucks.clicker_backend.utils;

import dev.brenolucks.clicker_backend.config.JwtProperties;
import dev.brenolucks.clicker_backend.domain.model.Users;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {
    private final JwtProperties jwtProperties;

    public JwtUtils(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String generateToken(Users users) {
        return Jwts.builder()
                .subject(users.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
                .signWith(getSigningKey())
                .compact();
    }

    public String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }

        return null;
    }

    public boolean tokenValid(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException ex) {
            throw new RuntimeException("Token expired: " + ex.getMessage(), ex);
        } catch (UnsupportedJwtException ex) {
            throw new RuntimeException("Token not supported: " + ex.getMessage(), ex);
        } catch (MalformedJwtException ex) {
            throw new RuntimeException("Token incorret: " + ex.getMessage(), ex);
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Token null or empty: " + ex.getMessage(), ex);
        }
    }

    public String getUsernameByToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }
}
