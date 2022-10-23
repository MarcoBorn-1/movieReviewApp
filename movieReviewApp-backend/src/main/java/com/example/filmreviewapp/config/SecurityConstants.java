package com.example.filmreviewapp.config;

public class SecurityConstants {

    public static final String SECRET = "SECRET_KEY";
    public static final long EXPIRATION_TIME = 1800000; // 30 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTH_PREFIX = "/api/auth";
    public static final String SIGN_UP_URL = "/register";
    public static final String LOGIN_URL = "/login";
}