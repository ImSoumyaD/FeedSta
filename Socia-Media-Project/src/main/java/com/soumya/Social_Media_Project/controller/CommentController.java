package com.soumya.Social_Media_Project.controller;

import com.soumya.Social_Media_Project.Exceptions.CommentException;
import com.soumya.Social_Media_Project.models.Comment;
import com.soumya.Social_Media_Project.models.User;
import com.soumya.Social_Media_Project.services.CommentService;
import com.soumya.Social_Media_Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    @PostMapping("/comment/post/{postId}")
    public Comment createComment(@RequestHeader("Authorization") String jwt, @RequestBody Comment comment, @PathVariable Integer postId) throws CommentException {
        try {
            User user = userService.getUserFromJwt(jwt);
            Comment newComment = commentService.createComment(comment, user.getId(), postId);
            newComment.getUser().setPassword(null);
            return newComment ;
        }catch (Exception e){
            throw new CommentException(e.getMessage());
        }
    }

    @PutMapping("/comment/like/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization") String jwt, @PathVariable Integer commentId) throws CommentException {
        try {
            User user = userService.getUserFromJwt(jwt);
            Comment likedComment = commentService.likeComment(commentId, user.getId());
            likedComment.getUser().setPassword(null);
            return likedComment;
        }catch (Exception e){
            throw new CommentException(e.getMessage());
        }
    }

    @GetMapping("/comment/find/{commentId}")
    public Comment getCommentById(@PathVariable Integer commentId) throws CommentException {
        return commentService.findCommentById(commentId);
    }

    @DeleteMapping("/comment/delete/{commentId}")
    public String deleteComment(@RequestHeader("Authorization") String jwt,@PathVariable Integer commentId) throws CommentException {
        try {
            User user = userService.getUserFromJwt(jwt);
            return commentService.deleteComment(user.getId(),commentId);
        }catch (Exception e){
            throw new CommentException(e.getMessage());
        }
    }
}
