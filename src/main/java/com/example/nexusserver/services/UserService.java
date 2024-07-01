package com.example.nexusserver.services;

import com.example.nexusserver.config.JwtProvider;
import com.example.nexusserver.entity.User;
import com.example.nexusserver.exceptions.UserException;
import com.example.nexusserver.repository.UserRepository;
import com.example.nexusserver.services.iservices.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;


    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Integer userId) throws UserException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found with id " + userId));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUser(User user, Integer userId) throws UserException {
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
        if (user.getGender() != null) {
            existingUser.setGender(user.getGender());
        }
        // Do not update password or followers/followings here for security and consistency
        return userRepository.save(existingUser);
    }

    @Override
    public User followUser(Integer reqUserId, Integer userId2) throws UserException {
        User reqUser = findUserById(reqUserId);
        User user2 = findUserById(userId2);

        List<Integer> followers = user2.getFollowers();
        List<Integer> followings = reqUser.getFollowings();

        if (!followers.contains(reqUser.getId())) {
            followers.add(reqUser.getId());
        }
        if (!followings.contains(user2.getId())) {
            followings.add(user2.getId());
        }

        user2.setFollowers(followers);
        reqUser.setFollowings(followings);

        userRepository.save(reqUser);
        userRepository.save(user2);

        return reqUser;
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);
        return user;
    }
}
