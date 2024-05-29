package com.example.nexusserver.controller;

import com.example.nexusserver.entity.User;
import com.example.nexusserver.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public List<User> findAllUser() {
        return userService.findAllUser();
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable Integer userId) throws Exception {
        return userService.findUserById(userId);
    }

    @PutMapping("/edit/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable Integer userId) throws Exception {
        return userService.updateUser(user, userId);
    }

    @PutMapping("/follow/{userId1}/{userId2}")
    public User followUserHandler(@PathVariable Integer userId1, @PathVariable Integer userId2) throws Exception {
        return userService.followUser(userId1, userId2);
    }

    @GetMapping("/search")
    public List<User> searchUser(@RequestParam("query") String query) {
        return userService.searchUser(query);
    }
}
