package com.example.nexusserver.services.iservices;

import com.example.nexusserver.entity.Chat;
import com.example.nexusserver.entity.Message;
import com.example.nexusserver.entity.User;

import java.util.List;

public interface IMessageService {
    public Message createMessage(User user, Integer chatId, Message req) throws Exception;

    public List<Message> findChatMessage(Integer chatId) throws Exception;
}
