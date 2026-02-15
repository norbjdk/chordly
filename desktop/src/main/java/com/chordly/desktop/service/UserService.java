package com.chordly.desktop.service;

import com.chordly.desktop.config.ApiConfig;
import com.chordly.desktop.model.entity.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class UserService {
    private final OkHttpClient client;
    private final ObjectMapper mapper;
    private static UserEntity currentUser;

    public UserService() {
        client = ApiConfig.getHttpClient();
        mapper = ApiConfig.getObjectMapper();
    }

    public UserEntity getCurrentUser() throws IOException {
        if (currentUser == null) {
            String userJson = TokenStorage.getUser();
            if (userJson != null && !userJson.isEmpty()) {
                try {
                    currentUser = mapper.readValue(userJson, UserEntity.class);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return currentUser;
    }

    public void setCurrentUser(UserEntity user) {
        currentUser = user;
        try {
            String userJson = mapper.writeValueAsString(user);
            TokenStorage.saveUser(userJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public boolean isLoggedIn() {
        try {
            return TokenStorage.isLoggedIn() && getCurrentUser() != null;
        } catch (IOException e) {
            return false;
        }
    }

    public static void logout() {
        currentUser = null;
        TokenStorage.clear();
    }

    public static String getUsername() {
        return currentUser != null ? currentUser.getUsername() : null;
    }

    public UserEntity getUserByUsername(String username) throws IOException {
        String url = ApiConfig.getBaseUrl() + "/api/v1/users/" + username;
        String token = TokenStorage.getToken();

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer " + token)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                return mapper.readValue(responseBody, UserEntity.class);
            } else {
                throw new IOException("Failed to fetch user: " + response.code());
            }
        }
    }
}