package com.example.nexusserver.services;

import com.example.nexusserver.entity.Chat;
import com.example.nexusserver.entity.Message;
import com.example.nexusserver.entity.User;
import com.example.nexusserver.repository.ChatRepository;
import com.example.nexusserver.repository.MessageRepository;
import com.example.nexusserver.services.iservices.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService {
    private final MessageRepository messageRepository;
    private final ChatService chatService;
    private final ChatRepository chatRepository;

    @Override
    public Message createMessage(User user, Integer chatId, Message req) throws Exception {
        Chat chat = chatService.findChatById(chatId);
        Message newMessage = new Message();

        newMessage.setChat(chat);
        newMessage.setImage(req.getImage());
        newMessage.setContent(req.getContent());
        newMessage.setUser(user);
        newMessage.setTimestamp(LocalDateTime.now());

        Message saveMessage = messageRepository.save(newMessage);
        chat.getMessages().add(saveMessage);

        chatRepository.save(chat);
        return saveMessage;
    }

    @Override
    public List<Message> findChatMessage(Integer chatId) throws Exception {
        Chat chat = chatService.findChatById(chatId);
        return messageRepository.findByChatId(chatId);
    }
}
