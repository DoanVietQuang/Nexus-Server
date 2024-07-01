package com.example.nexusserver.controller;

import com.example.nexusserver.entity.Chat;
import com.example.nexusserver.entity.User;
import com.example.nexusserver.request.CreateChatRequest;
import com.example.nexusserver.services.ChatService;
import com.example.nexusserver.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Chat> createChat(@RequestHeader("Authorization") String jwt, @RequestBody CreateChatRequest req) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        User secondUser = userService.findUserById(req.getUserId());
        Chat chat = chatService.createChat(reqUser, secondUser);
        return new ResponseEntity<>(chat, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Chat>> findUserChat(@RequestHeader("Authorization") String jwt) {
        User reqUser = userService.findUserByJwt( jwt );
        List<Chat> chats = chatService.findUsersChat(reqUser.getId());
        return new ResponseEntity<List<Chat>>(chats, HttpStatus.ACCEPTED);
    }

}
