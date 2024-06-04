package com.example.nexusserver.services.iservices;

import com.example.nexusserver.entity.Chat;
import com.example.nexusserver.entity.User;

import java.util.List;

public interface IChatService {
    public Chat createChat(User reqUser, User secondUser);

    public Chat findChatById(Integer chatId) throws Exception;

    public List<Chat> findUsersChat(Integer userId);
}

