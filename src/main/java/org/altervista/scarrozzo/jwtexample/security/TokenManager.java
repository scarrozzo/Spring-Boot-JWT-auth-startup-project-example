package org.altervista.scarrozzo.jwtexample.security;

import antlr.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

public class TokenManager {
    public static final String CUSTOM_CLAIM = "custom_claim";
    private static Logger logger = LoggerFactory.getLogger(TokenManager.class);
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public static final String TOKEN_PREFIX = "Bearer ";
    private static final int TOKEN_EXPIRY_DURATION = 24; // in hours

    public static String generateToken(String subject) {
        String token = Jwts.builder()
                .setSubject(subject)
                .setExpiration(Date.from(ZonedDateTime.now().plusHours(TOKEN_EXPIRY_DURATION).toInstant()))
                .claim(CUSTOM_CLAIM, UUID.randomUUID().toString())
                .signWith(SECRET_KEY)
                .compact();
        logger.info("generated token: {}", token);
        return token;
    }

    public static String parseToken(String token) {
        logger.info("parsing token: {}", token);
         Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();
        String subject = claims.getSubject();
        String customClaim = (String)claims.get(CUSTOM_CLAIM);
        logger.info("subject: {}", subject);
        logger.info("{}: {}", CUSTOM_CLAIM, customClaim);
        return subject;
    }
}
