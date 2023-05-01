package com.project.mini.peer2peer.controller;

import com.project.mini.peer2peer.model.User;
import com.project.mini.peer2peer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public @ResponseBody List<User> selectAllUser() {
        return userService.allUsers();
    }
    @GetMapping("/id")
    public @ResponseBody User selectUniqueUser(@RequestParam("id") int id) {
        return userService.findTheUser(id);
    }
    @GetMapping("/add")
    public String addUser(@RequestParam("user_id") int userId,
                          @RequestParam("name") String name,
                          @RequestParam("email") String email,
                          @RequestParam("password") String password) {
        User model = new User(userId, name, email, password, 0.00, false);
        userService.insert(model);
        return "redirect:/user/";
    }
    @GetMapping("/update")
    public String updateUser(@RequestParam("user_id") int userId,
                             @RequestParam("name") String name,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password,
                             @RequestParam("balance") Double balance,
                             @RequestParam("delete_status") Boolean deleteStatus) {
        User model = new User(userId, name, email, password, balance, deleteStatus);
        userService.update(model);
        return "redirect:/user/";
    }
}
