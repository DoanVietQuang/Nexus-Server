package com.example.nexusserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;

    @JsonIgnore
    @Column(name = "followers")
    private String followersJson = "[]";

    @JsonIgnore
    @Column(name = "followings")
    private String followingsJson = "[]";

    @JsonIgnore
    @ManyToMany
    private List<Post> savedPost = new ArrayList<>();

    @Transient
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Converts the JSON string followersJson to a List<Integer>.
     *
     * @return List of follower IDs. If conversion fails, returns an empty list.
     */
    public List<Integer> getFollowers() {
        try {
            // Convert JSON string to List<Integer>
            return objectMapper.readValue(followersJson, List.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // Return empty list if conversion fails
            return new ArrayList<>();
        }
    }

    /**
     * Converts a List<Integer> of follower IDs to a JSON string and stores it in followersJson.
     *
     * @param followers List of follower IDs.
     */
    public void setFollowers(List<Integer> followers) {
        try {
            // Convert List<Integer> to JSON string
            this.followersJson = objectMapper.writeValueAsString(followers);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // Set to empty JSON array if conversion fails
            this.followersJson = "[]";
        }
    }

    /**
     * Converts the JSON string followingsJson to a List<Integer>.
     *
     * @return List of following IDs. If conversion fails, returns an empty list.
     */
    public List<Integer> getFollowings() {
        try {
            // Convert JSON string to List<Integer>
            return objectMapper.readValue(followingsJson, List.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // Return empty list if conversion fails
            return new ArrayList<>();
        }
    }

    /**
     * Converts a List<Integer> of following IDs to a JSON string and stores it in followingsJson.
     *
     * @param followings List of following IDs.
     */
    public void setFollowings(List<Integer> followings) {
        try {
            // Convert List<Integer> to JSON string
            this.followingsJson = objectMapper.writeValueAsString(followings);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // Set to empty JSON array if conversion fails
            this.followingsJson = "[]";
        }
    }
}
