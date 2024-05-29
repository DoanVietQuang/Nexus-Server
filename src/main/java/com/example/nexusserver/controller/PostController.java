package com.example.nexusserver.controller;

import com.example.nexusserver.entity.Post;
import com.example.nexusserver.response.ApiResponse;
import com.example.nexusserver.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/posts")
public class PostController {
    private final PostService postService;

    @GetMapping("")
    public ResponseEntity<List<Post>> findAllPost(){
        List<Post> post = postService.findAllPost();
        return new ResponseEntity<List<Post>>(post,HttpStatus.ACCEPTED);
    }

    @PostMapping("/create/user/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer userId) throws Exception {
        Post newPost = postService.createNewPost(post, userId);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable Integer postId) throws Exception {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> deletedPost(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
        String message = postService.deletePost(postId, userId);
        ApiResponse apiResponse = new ApiResponse(message, true);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<Post>> findUserPost(@PathVariable Integer userId) {
        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @PutMapping("/save/{postId}/user/{userId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId,@PathVariable Integer userId) throws Exception {
        Post post = postService.savedPost(postId,userId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @PutMapping("/like/{postId}/user/{userId}")
    public ResponseEntity<Post> likedPostHandler(@PathVariable Integer postId,@PathVariable Integer userId) throws Exception {
        Post post = postService.likePost(postId,userId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }
}
