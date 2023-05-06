package com.project.mini.peer2peeruser.controller;

import com.project.mini.peer2peeruser.model.User;
import com.project.mini.peer2peeruser.request.UserRequest;
import com.project.mini.peer2peeruser.response.MessageResponse;
import com.project.mini.peer2peeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageResponse<List<User>>> selectAllUser() {
        Map<String, Object> res = new HashMap<>();
        res.put("message", "success get all user data");
        res.put("data", userService.allUsers());
        return new ResponseEntity(res, HttpStatus.OK);
    }
    @GetMapping("/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageResponse<User>> selectUniqueUser(@PathVariable("user_id") int userId) {
        Map<String, Object> res = new HashMap<>();
        res.put("message", "success find user data");
        res.put("data", userService.findTheUser(userId));
        return new ResponseEntity(res, HttpStatus.OK);
    }
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageResponse> addUser (@RequestBody UserRequest userRequest) {
        User user = new User(userRequest);
        userService.insert(user);
        Map<String, Object> res = new HashMap<>();
        res.put("message", "success add user data");
        return new ResponseEntity(res, HttpStatus.OK);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageResponse<User>> updateUser (
            @RequestBody User user
    ) {
        userService.update(user);
        Map<String, Object> res = new HashMap<>();
        res.put("message", "success update user data");
        return new ResponseEntity(res, HttpStatus.OK);
    }
}
