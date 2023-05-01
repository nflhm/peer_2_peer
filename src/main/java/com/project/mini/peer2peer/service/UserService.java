package com.project.mini.peer2peer.service;

import com.project.mini.peer2peer.model.User;
import com.project.mini.peer2peer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public void insert(User user) { userRepository.save(user); }

    public void update(User user) {
        userRepository.save(user);
    }

    public User findTheUser(Integer integer) {
        return userRepository.findUserByUserId(integer);
    }

    public List<User> allUsers() {
        return userRepository.findAllByOrderByUserId();
    }
}
