package com.chordly.server.service;

import com.chordly.server.model.dto.FriendResponse;
import com.chordly.server.model.entity.FriendshipEntity;
import com.chordly.server.model.entity.UserEntity;
import com.chordly.server.model.repo.FriendshipRepo;
import com.chordly.server.model.repo.UserRepo;
import com.chordly.server.model.typo.FriendshipStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FriendshipService {

    @Autowired
    private FriendshipRepo friendshipRepository;

    @Autowired
    private UserRepo userRepository;

    public FriendResponse sendFriendRequest(String senderUsername, Long receiverId) {
        UserEntity sender = userRepository.findByUsername(senderUsername)
                .orElseThrow(() -> new RuntimeException("User not found: " + senderUsername));

        UserEntity receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + receiverId));

        if (friendshipRepository.existsByUserIdAndFriendId(sender.getId(), receiverId)) {
            throw new RuntimeException("Friendship request already exists");
        }

        if (sender.getId().equals(receiverId)) {
            throw new RuntimeException("Cannot add yourself as a friend");
        }

        FriendshipEntity friendship = new FriendshipEntity();
        friendship.setUser(sender);
        friendship.setFriend(receiver);
        friendship.setStatus(FriendshipStatus.PENDING);
        friendship.setCreatedAt(LocalDateTime.now());

        friendship = friendshipRepository.save(friendship);
        return convertToDTO(friendship);
    }

    public FriendResponse acceptFriendRequest(String receiverUsername, Long friendshipId) {
        FriendshipEntity friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new RuntimeException("Friendship not found"));

        if (!friendship.getFriend().getUsername().equals(receiverUsername)) {
            throw new RuntimeException("Not authorized to accept this request");
        }

        if (friendship.getStatus() != FriendshipStatus.PENDING) {
            throw new RuntimeException("Friendship is not in pending state");
        }

        friendship.setStatus(FriendshipStatus.ACCEPTED);

        friendship = friendshipRepository.save(friendship);
        return convertToDTO(friendship);
    }

    public FriendResponse rejectFriendRequest(String receiverUsername, Long friendshipId) {
        FriendshipEntity friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new RuntimeException("Friendship not found"));

        if (!friendship.getFriend().getUsername().equals(receiverUsername)) {
            throw new RuntimeException("Not authorized to reject this request");
        }

        friendship = friendshipRepository.save(friendship);
        return convertToDTO(friendship);
    }

    public void removeFriend(String username, Long friendId) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        friendshipRepository.deleteByUserIdAndFriendId(user.getId(), friendId);
        friendshipRepository.deleteByUserIdAndFriendId(friendId, user.getId());
    }

    public List<FriendResponse> getPendingRequests(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return friendshipRepository.findByFriendIdAndStatus(user.getId(), FriendshipStatus.PENDING)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<FriendResponse> getFriends(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return friendshipRepository.findByUserIdAndStatus(user.getId(), FriendshipStatus.ACCEPTED)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public String getFriendshipStatus(String username, Long friendId) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return friendshipRepository.findByUserIdAndFriendId(user.getId(), friendId)
                .map(friendship -> friendship.getStatus().name())
                .orElse("NO_RELATION");
    }

    private FriendResponse convertToDTO(FriendshipEntity friendship) {
        FriendResponse dto = new FriendResponse();
        dto.setId(friendship.getId());
        dto.setUserId(friendship.getUser().getId());
        dto.setUserUsername(friendship.getUser().getUsername());
        dto.setFriendId(friendship.getFriend().getId());
        dto.setFriendUsername(friendship.getFriend().getUsername());
        dto.setStatus(friendship.getStatus().name());
        dto.setCreatedAt(friendship.getCreatedAt());
        return dto;
    }
}
