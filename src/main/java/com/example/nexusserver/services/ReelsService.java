package com.example.nexusserver.services;

import com.example.nexusserver.entity.Reels;
import com.example.nexusserver.entity.User;
import com.example.nexusserver.repository.ReelsRepository;
import com.example.nexusserver.services.iservices.IReelsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReelsService implements IReelsService {
    private final ReelsRepository reelsRepository;
    private final UserService userService;

    @Override
    public Reels createReels(Reels reels, User user) {
        Reels newReels = new Reels();
        newReels.setTitle(reels.getTitle());
        newReels.setVideo(reels.getVideo());
        newReels.setUser(user);

        Reels savedReels = reelsRepository.save(newReels);
        return savedReels;
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUserReels(Integer userId) throws Exception {
        userService.findUserById(userId);

        return reelsRepository.findReelsByUserId(userId);
    }
}
