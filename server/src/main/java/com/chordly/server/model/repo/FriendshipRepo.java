package com.chordly.server.model.repo;

import com.chordly.server.model.entity.FriendshipEntity;
import com.chordly.server.model.typo.FriendshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepo extends JpaRepository<FriendshipEntity, Long> {
    Optional<FriendshipEntity> findByUserIdAndFriendId(Long userId, Long friendId);
    List<FriendshipEntity> findByUserIdAndStatus(Long userId, FriendshipStatus status);
    List<FriendshipEntity> findByFriendIdAndStatus(Long friendId, FriendshipStatus status);
    boolean existsByUserIdAndFriendId(Long userId, Long friendId);
    void deleteByUserIdAndFriendId(Long userId, Long friendId);
    List<FriendshipEntity> findByUserId(Long userId);
    List<FriendshipEntity> findByFriendId(Long friendId);

    @Query("SELECT f FROM FriendshipEntity f WHERE " +
            "(f.user.id = :userId1 AND f.friend.id = :userId2) OR " +
            "(f.user.id = :userId2 AND f.friend.id = :userId1)")
    List<FriendshipEntity> findRelationshipBetweenUsers(@Param("userId1") Long userId1,
                                                        @Param("userId2") Long userId2);

    @Query("SELECT f FROM FriendshipEntity f WHERE " +
            "(f.user.id = :userId OR f.friend.id = :userId) AND f.status = 'ACCEPTED'")
    List<FriendshipEntity> findAllAcceptedFriendships(@Param("userId") Long userId);
}