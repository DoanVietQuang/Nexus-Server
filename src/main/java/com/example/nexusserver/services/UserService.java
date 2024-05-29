package com.example.nexusserver.services;

import com.example.nexusserver.entity.User;
import com.example.nexusserver.repository.UserRepository;
import com.example.nexusserver.services.iservices.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(User user) {
        user.setFollowers(new ArrayList<>()); // Ensure lists are initialized
        user.setFollowings(new ArrayList<>());
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        return userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found with id " + userId));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUser(User user, Integer userId) throws Exception {
        User existingUser = findUserById(userId);
        if (user.getFirstName() != null) {
            existingUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            existingUser.setLastName(user.getLastName());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        // Do not update password or followers/followings here for security and consistency
        return userRepository.save(existingUser);
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws Exception {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        List<Integer> followers = user2.getFollowers();
        List<Integer> followings = user1.getFollowings();

        if (!followers.contains(user1.getId())) {
            followers.add(user1.getId());
        }
        if (!followings.contains(user2.getId())) {
            followings.add(user2.getId());
        }

        user2.setFollowers(followers);
        user1.setFollowings(followings);

        userRepository.save(user1);
        userRepository.save(user2);

        return user1;
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }
}
