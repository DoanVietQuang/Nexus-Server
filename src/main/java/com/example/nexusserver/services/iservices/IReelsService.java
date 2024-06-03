package com.example.nexusserver.services.iservices;

import com.example.nexusserver.entity.Reels;
import com.example.nexusserver.entity.User;

import java.util.List;

public interface IReelsService {
    public Reels createReels(Reels reels, User user);

    public List<Reels> findAllReels();

    public List<Reels> findUserReels(Integer userId) throws Exception;

}
