package com.example.nexusserver.controller;

import com.example.nexusserver.entity.Post;
import com.example.nexusserver.entity.User;
import com.example.nexusserver.response.ApiResponse;
import com.example.nexusserver.services.PostService;
import com.example.nexusserver.services.UserService;
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
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<Post>> findAllPost() {
        List<Post> post = postService.findAllPost();
        return new ResponseEntity<List<Post>>(post, HttpStatus.ACCEPTED);
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String jwt,
                                           @RequestBody Post post) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post newPost = postService.createNewPost(post, reqUser.getId());
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable Integer postId) throws Exception {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<ApiResponse> deletedPost(@PathVariable Integer postId,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        String message = postService.deletePost(postId, reqUser.getId());
        ApiResponse apiResponse = new ApiResponse(message, true);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<Post>> findUserPost(@PathVariable Integer userId) {
        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @PutMapping("/save/{postId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post post = postService.savedPost(postId, reqUser.getId());
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @PutMapping("/like/{postId}")
    public ResponseEntity<Post> likedPostHandler(@PathVariable Integer postId,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post post = postService.likePost(postId, reqUser.getId());
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }
}
