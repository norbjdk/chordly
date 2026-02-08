package com.chordly.server.controller;

import com.chordly.server.model.dto.FriendRequest;
import com.chordly.server.model.dto.FriendResponse;
import com.chordly.server.service.FriendshipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/friends")
@CrossOrigin(origins = "http://localhost:5173")
public class FriendshipController {

    @Autowired
    private FriendshipService friendshipService;

    @PostMapping("/request")
    public ResponseEntity<FriendResponse> sendFriendRequest(@Valid @RequestBody FriendRequest requestDTO, Authentication authentication) {

        String currentUsername = authentication.getName();
        FriendResponse response = friendshipService.sendFriendRequest(currentUsername, requestDTO.getFriendId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{friendshipId}/accept")
    public ResponseEntity<FriendResponse> acceptFriendRequest(@PathVariable Long friendshipId, Authentication authentication) {
        String currentUsername = authentication.getName();
        FriendResponse response = friendshipService.acceptFriendRequest(currentUsername, friendshipId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{friendshipId}/reject")
    public ResponseEntity<FriendResponse> rejectFriendRequest(@PathVariable Long friendshipId, Authentication authentication) {
        String currentUsername = authentication.getName();
        FriendResponse response = friendshipService.rejectFriendRequest(currentUsername, friendshipId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{friendId}")
    public ResponseEntity<Void> removeFriend(
            @PathVariable Long friendId,
            Authentication authentication) {

        String currentUsername = authentication.getName();
        friendshipService.removeFriend(currentUsername, friendId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pending")
    public ResponseEntity<List<FriendResponse>> getPendingRequests(Authentication authentication) {
        String currentUsername = authentication.getName();
        List<FriendResponse> requests = friendshipService.getPendingRequests(currentUsername);
        return ResponseEntity.ok(requests);
    }

    @GetMapping
    public ResponseEntity<List<FriendResponse>> getFriends(Authentication authentication) {
        String currentUsername = authentication.getName();
        List<FriendResponse> friends = friendshipService.getFriends(currentUsername);
        return ResponseEntity.ok(friends);
    }

    @GetMapping("/status/{friendId}")
    public ResponseEntity<String> getFriendshipStatus(
            @PathVariable Long friendId,
            Authentication authentication) {

        String currentUsername = authentication.getName();
        String status = friendshipService.getFriendshipStatus(currentUsername, friendId);
        return ResponseEntity.ok(status);
    }
}
