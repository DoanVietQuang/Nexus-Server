package com.example.nexusserver.services.iservices;

import com.example.nexusserver.entity.Post;

import java.util.List;

public interface IPostService {
    List<Post> findAllPost();

    Post createNewPost(Post post, Integer userId) throws Exception;

    List<Post> findPostByUserId(Integer userId);

    Post findPostById(Integer postId);

    Post savedPost(Integer postId, Integer userId);

    String deletePost(Integer postId, Integer userId);
}
