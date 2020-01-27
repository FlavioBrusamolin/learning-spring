package com.leucotron.learningspring.config.security;

/**
 *
 * @author flavio
 */
public final class JSecurityConstants {
    
    public static final String AUTH_URL = "/api/v1/auth";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_HEADER = "Authorization";    
    public static final String JWT_SECRET = "learning_spring_secret_key";    
    public static final Integer JWT_EXPIRATION = 43200000;
    
}
