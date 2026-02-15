package com.chordly.desktop.service;

import com.chordly.desktop.config.ApiConfig;
import com.chordly.desktop.model.dto.external.LoginRequest;
import com.chordly.desktop.model.dto.external.LoginResponse;
import com.chordly.desktop.model.entity.UserEntity;
import com.chordly.desktop.model.event.EventBus;
import com.chordly.desktop.model.event.LoginFailedEvent;
import com.chordly.desktop.model.event.LoginSuccessEvent;
import com.chordly.desktop.model.event.LogoutEvent;
import com.chordly.desktop.util.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public class AuthService {
    private final OkHttpClient client;
    private final ObjectMapper mapper;
    private final UserService userService;

    public AuthService() {
        this.client = ApiConfig.getHttpClient();
        this.mapper = ApiConfig.getObjectMapper();
        this.userService = new UserService();
    }

    public void login(String username, String password) {
        new Thread(() -> {
            try {
                LoginRequest loginRequest = new LoginRequest(username, password);
                String jsonBody = mapper.writeValueAsString(loginRequest);

                RequestBody body = RequestBody.create(
                        jsonBody,
                        MediaType.get("application/json")
                );

                String url = ApiConfig.getBaseUrl() + "/api/v1/auth/login";

                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (response.isSuccessful() && response.body() != null) {
                        String responseBody = response.body().string();
                        LoginResponse loginResponse = mapper.readValue(responseBody, LoginResponse.class);

                        TokenStorage.saveToken(loginResponse.getToken());

                        UserEntity user = new UserEntity();
                        user.setUsername(loginResponse.getUsername());
                        user.setEmail(loginResponse.getEmail());

                        userService.setCurrentUser(user);

                        Logger.debug("Login successful: " + user.getUsername());
                        EventBus.getInstance().publish(new LoginSuccessEvent(user));

                    } else {
                        String errorMsg = "Login failed: " + response.code();
                        if (response.body() != null) {
                            errorMsg += " - " + response.body().string();
                        }
                        Logger.err(errorMsg);
                        EventBus.getInstance().publish(new LoginFailedEvent(errorMsg));
                    }
                }

            } catch (IOException e) {
                Logger.err("Login error: " + e.getMessage());
                e.printStackTrace();
                EventBus.getInstance().publish(new LoginFailedEvent("Connection error: " + e.getMessage()));
            }
        }).start();
    }

    public void logout() {
        UserService.logout();
        Logger.debug("User logged out");
        EventBus.getInstance().publish(new LogoutEvent());
    }

    public boolean isLoggedIn() {
        return userService.isLoggedIn();
    }
}