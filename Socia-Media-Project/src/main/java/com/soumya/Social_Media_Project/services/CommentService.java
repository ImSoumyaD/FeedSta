package com.soumya.Social_Media_Project.services;

import com.soumya.Social_Media_Project.Exceptions.CommentException;
import com.soumya.Social_Media_Project.models.Comment;

public interface CommentService {
    public Comment createComment(Comment comment, Integer userId, Integer postId) throws CommentException;
    public Comment findCommentById(Integer commentId) throws CommentException;
    public Comment likeComment(Integer commentId,Integer userId) throws CommentException;
    public String deleteComment(Integer userId,Integer commentId) throws CommentException;
}
