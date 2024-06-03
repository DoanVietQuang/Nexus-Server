package com.example.nexusserver.controller;

import com.example.nexusserver.entity.Story;
import com.example.nexusserver.entity.User;
import com.example.nexusserver.services.StoryService;
import com.example.nexusserver.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stories")
public class StoryController {
    private final StoryService storyService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Story> createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);
        Story newStory = storyService.createStory(story, user);
        return new ResponseEntity<>(newStory, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Story>> findStoryByUserId(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        List<Story> story = storyService.findStoryByUserId(user.getId());
        return new ResponseEntity<List<Story>>(story, HttpStatus.ACCEPTED);
    }
}
