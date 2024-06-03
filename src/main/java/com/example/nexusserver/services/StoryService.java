package com.example.nexusserver.services;

import com.example.nexusserver.entity.Story;
import com.example.nexusserver.entity.User;
import com.example.nexusserver.repository.StoryRepository;
import com.example.nexusserver.services.iservices.IStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoryService implements IStoryService {
    private final StoryRepository storyRepository;
    private final UserService userService;

    @Override
    public Story createStory(Story story, User user) {
        Story newStory = new Story();

        newStory.setCaptions(story.getCaptions());
        newStory.setImage(story.getImage());
        newStory.setUser(user);
        newStory.setTimestamp(LocalDateTime.now());

        Story savedStory = storyRepository.save(newStory);
        return savedStory;
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) throws Exception {
        userService.findUserById(userId);
        return storyRepository.findStoryByUserId(userId);
    }
}
