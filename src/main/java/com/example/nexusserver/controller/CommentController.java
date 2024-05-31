package com.example.nexusserver.controller;

import com.example.nexusserver.entity.Comment;
import com.example.nexusserver.entity.User;
import com.example.nexusserver.services.CommentService;
import com.example.nexusserver.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;

    @PostMapping("/create/post/{postId}")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment,
                                                 @PathVariable Integer postId,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Comment newComment = commentService.createComment(comment, postId, user.getId());
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);

    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> findCommentById(@PathVariable Integer commentId) throws Exception {
        Comment comment = commentService.findCommentById(commentId);
        return new ResponseEntity<>(comment, HttpStatus.ACCEPTED);
    }

    @PutMapping("/like/{commentId}")
    public ResponseEntity<Comment> likeComment(@PathVariable Integer commentId,
                                               @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Comment likeComment = commentService.likeComment(commentId, user.getId());
        return new ResponseEntity<>(likeComment, HttpStatus.ACCEPTED);

    }

}
