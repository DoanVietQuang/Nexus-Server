package com.example.nexusserver.services.iservices;

import com.example.nexusserver.entity.Story;
import com.example.nexusserver.entity.User;

import java.util.List;

public interface IStoryService {
    public Story createStory(Story story, User user);
    public List<Story> findStoryByUserId(Integer userId) throws Exception;

}
