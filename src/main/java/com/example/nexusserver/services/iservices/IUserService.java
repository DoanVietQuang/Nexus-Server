package com.example.nexusserver.services.iservices;

import com.example.nexusserver.entity.User;
import com.example.nexusserver.exceptions.UserException;

import java.util.List;

public interface IUserService {
    public List<User> findAllUser();

    public User findUserById(Integer userId) throws Exception;

    public User findUserByEmail(String email);

    public User updateUser(User user, Integer userId) throws UserException;

    public User followUser(Integer userId1, Integer userId2) throws UserException;

    public List<User> searchUser(String query);

    public User findUserByJwt(String jwt);
}
