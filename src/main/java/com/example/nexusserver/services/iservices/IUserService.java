package com.example.nexusserver.services.iservices;

import com.example.nexusserver.entity.User;

import java.util.List;

public interface IUserService {
    public List<User> findAllUser();

    public User registerUser(User user);

    public User findUserById(Integer userId) throws Exception;

    public User findUserByEmail(String email);

    public User updateUser(User user, Integer userId) throws Exception;

    public User followUser(Integer userId1, Integer userId2) throws Exception;

    public List<User> searchUser(String query);

    public User findUserByJwt(String jwt);
}
