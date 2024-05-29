package com.example.nexusserver.repository;

import com.example.nexusserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String email);
    @Query("select u from User u where u.firstName LIKE %:query% OR u.lastName like %:query% OR u.email like %:query%")
    List<User> searchUser(@Param("query")String query);
}
