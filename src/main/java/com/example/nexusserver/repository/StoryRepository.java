package com.example.nexusserver.repository;

import com.example.nexusserver.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {
    public List<Story>findStoryByUserId(Integer userId);
}
