package com.example.nexusserver.controller;

import com.example.nexusserver.entity.User;
import com.example.nexusserver.exceptions.UserException;
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


    @GetMapping("/{userId}")
    public User findUserById(@PathVariable Integer userId) throws UserException {
        return userService.findUserById(userId);
    }

    @PutMapping("/edit")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        return userService.updateUser(user, reqUser.getId());
    }

    @PutMapping("/follow/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt,
                                  @PathVariable Integer userId2) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        return userService.followUser(reqUser.getId(), userId2);
    }

    @GetMapping("/search")
    public List<User> searchUser(@RequestParam("query") String query) {
        return userService.searchUser(query);
    }

    @GetMapping("/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
//        System.out.println("jwt----" + jwt);
        User user = userService.findUserByJwt(jwt);
        user.setPassword(null);
        return user;
    }

}
