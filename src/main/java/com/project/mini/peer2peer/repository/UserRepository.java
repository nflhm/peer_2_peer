package com.project.mini.peer2peer.repository;

import com.project.mini.peer2peer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserId(Integer integer);
    List<User> findAllByOrderByUserId();
}
