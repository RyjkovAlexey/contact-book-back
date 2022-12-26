package ru.alexey.contactbook.contactbookback.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenService {

    private final Algorithm hmac512;
    private final JWTVerifier verifier;

    private final Long JWT_TOKEN_VALIDITY;

    private final Logger logger = LoggerFactory.getLogger(JwtTokenService.class);

    public JwtTokenService(@Value("${jwt.secret}") final String secret, @Value("${jwt.validity}") Long JWT_TOKEN_VALIDITY) {
        this.hmac512 = Algorithm.HMAC512(secret);
        this.verifier = JWT.require(this.hmac512).build();
        this.JWT_TOKEN_VALIDITY = JWT_TOKEN_VALIDITY;
    }

    public String generateToken(final UserDetails userDetails) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .sign(this.hmac512);
    }

    public String validateTokenAndGetUsername(final String token) {
        try {
            return verifier.verify(token).getSubject();
        } catch (final JWTVerificationException verificationEx) {
            logger.warn("token invalid: {}", verificationEx.getMessage());
            return null;
        }
    }
}
