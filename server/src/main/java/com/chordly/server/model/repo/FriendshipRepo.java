package com.chordly.server.model.repo;

import com.chordly.server.model.entity.FriendshipEntity;
import com.chordly.server.model.typo.FriendshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepo extends JpaRepository<FriendshipEntity, Long> {
    Optional<FriendshipEntity> findByUserIdAndFriendId(Long userId, Long friendId);
    List<FriendshipEntity> findByUserIdAndStatus(Long userId, FriendshipStatus status);
    boolean existsByUserIdAndFriendId(Long userId, Long friendId);
    void deleteByUserIdAndFriendId(Long userId, Long friendId);
}
