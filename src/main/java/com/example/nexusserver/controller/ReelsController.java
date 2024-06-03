package com.example.nexusserver.controller;

import com.example.nexusserver.entity.Reels;
import com.example.nexusserver.entity.User;
import com.example.nexusserver.services.ReelsService;
import com.example.nexusserver.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reels")
public class ReelsController {

    private final ReelsService reelsService;
    private final UserService userService;


    @GetMapping()
    public ResponseEntity<List<Reels>> findAllReels() {
        List<Reels> reels = reelsService.findAllReels();
        return new ResponseEntity<List<Reels>>(reels, HttpStatus.ACCEPTED);
    }

    @PostMapping("/create")
    public ResponseEntity<Reels> createReels(@RequestBody Reels reels, @RequestHeader("Authorization") String jwt) {
        User reqUser = userService.findUserByJwt(jwt);
        Reels newReels = reelsService.createReels(reels, reqUser);
        return new ResponseEntity<>(newReels, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Reels>> findUserReels(@RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        List<Reels> userReels = reelsService.findUserReels(reqUser.getId());
        return new ResponseEntity<>(userReels, HttpStatus.ACCEPTED);
    }
}
