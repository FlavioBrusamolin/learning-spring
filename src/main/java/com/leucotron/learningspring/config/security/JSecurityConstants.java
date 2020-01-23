package com.leucotron.learningspring.config.security;

import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author flavio
 */
public final class JSecurityConstants {
    
    public static final String AUTH_URL = "/api/v1/auth";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_HEADER = "Authorization";
    
//    @Value("${security.jwt.secret}")
    public static String JWT_SECRET = "learning_spring_secret_key";
    
//    @Value("${security.jwt.expiration}")
    public static Integer JWT_EXPIRATION = 43200000;
    
}
