package com.example.nexusserver.services;

import com.example.nexusserver.entity.Chat;
import com.example.nexusserver.entity.User;
import com.example.nexusserver.repository.ChatRepository;
import com.example.nexusserver.services.iservices.IChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService implements IChatService {
    private final ChatRepository chatRepository;

    @Override
    public Chat createChat(User reqUser, User secondUser) {
        Chat isExist = chatRepository.findChatByUsersId(secondUser, reqUser);
        if (isExist != null) {
            return isExist;
        }
        Chat newChat = new Chat();
        newChat.getUsers().add(secondUser);
        newChat.getUsers().add(reqUser);
        newChat.setTimestamp(LocalDateTime.now());
        Chat savedChat = chatRepository.save(newChat);
        return savedChat;
    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {
        Optional<Chat> optionalChat = chatRepository.findById(chatId);
        if (optionalChat.isEmpty()) {
            throw new Exception("chat not found with id " + chatId);
        }
        return optionalChat.get();
    }

    @Override
    public List<Chat> findUsersChat(Integer userId) {
        return chatRepository.findByUsersId(userId);
    }
}
