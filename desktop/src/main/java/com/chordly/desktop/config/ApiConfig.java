package com.chordly.desktop.config;

import com.chordly.desktop.service.TokenStorage;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;

public class ApiConfig {
    private static final String BASE_URL = "http://localhost:8080/api/v1";
    private static OkHttpClient httpClient;
    private static ObjectMapper objectMapper;

    static {
        httpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
                var originalRequest = chain.request();
                var token = TokenStorage.getToken();

                if (token != null && !token.isEmpty()) {
                    var requestBuilder = originalRequest.newBuilder().header("Authorization","Bearer " + token);
                    return chain.proceed(requestBuilder.build());
                }
           return chain.proceed(originalRequest);
        }).build();

        objectMapper = new ObjectMapper();
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static OkHttpClient getHttpClient() {
        return httpClient;
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
