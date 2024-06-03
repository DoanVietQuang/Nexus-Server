package com.example.nexusserver.services.iservices;

import com.example.nexusserver.entity.Comment;

public interface ICommentService {

    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception;

    public Comment findCommentById(Integer commentId) throws Exception;

    public Comment likeComment(Integer commentId,Integer userId) throws Exception;

}
