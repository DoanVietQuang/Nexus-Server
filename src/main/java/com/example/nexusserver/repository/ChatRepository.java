package com.example.nexusserver.repository;

import com.example.nexusserver.entity.Chat;
import com.example.nexusserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    public List<Chat> findByUsersId(Integer userId);

    @Query("SELECT c FROM Chat c WHERE :user MEMBER OF c.users AND :reqUser MEMBER OF c.users ")
    public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);
}
