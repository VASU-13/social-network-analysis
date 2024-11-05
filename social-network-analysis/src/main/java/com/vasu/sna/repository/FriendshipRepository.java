package com.vasu.sna.repository;

import com.vasu.sna.entity.Friendship;
import com.vasu.sna.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship,Integer> {

    Optional<Friendship> findByUser1UserIdAndUser2UserId(Integer userId1, Integer userId2);

    List<Friendship> findByUser1UserId(Integer userId);
}
