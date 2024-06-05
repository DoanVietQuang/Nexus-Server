package com.example.nexusserver.controller;

import com.example.nexusserver.entity.Message;
import com.example.nexusserver.entity.User;
import com.example.nexusserver.services.MessageService;
import com.example.nexusserver.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;

    @PostMapping("/create/{chatId}")
    public ResponseEntity<Message> createMessage(@RequestBody Message req, @RequestHeader("Authorization") String jwt, @PathVariable Integer chatId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Message message = messageService.createMessage(user, chatId, req);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Message>> findChatMessage(@RequestHeader("Authorization") String jwt, @PathVariable Integer chatId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        List<Message> messages = messageService.findChatMessage(chatId);
        return new ResponseEntity<List<Message>>(messages, HttpStatus.CREATED);
    }


}
