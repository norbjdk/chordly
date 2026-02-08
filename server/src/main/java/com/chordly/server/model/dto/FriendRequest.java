package com.chordly.server.model.dto;

import jakarta.validation.constraints.NotNull;

public class FriendRequest {
    @NotNull(message = "Friend ID is required")
    private Long friendId;

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }
}
