package com.project.mini.peer2peeruser.repository;

import com.project.mini.peer2peeruser.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String username);
    User findUserByUserId(Integer integer);
    List<User> findAllByOrderByUserId();
}
