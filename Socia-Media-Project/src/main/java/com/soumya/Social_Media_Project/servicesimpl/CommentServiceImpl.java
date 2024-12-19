package com.soumya.Social_Media_Project.servicesimpl;

import com.soumya.Social_Media_Project.Exceptions.CommentException;
import com.soumya.Social_Media_Project.models.Comment;
import com.soumya.Social_Media_Project.models.Post;
import com.soumya.Social_Media_Project.models.User;
import com.soumya.Social_Media_Project.repo.CommentRepository;
import com.soumya.Social_Media_Project.repo.PostRepo;
import com.soumya.Social_Media_Project.services.CommentService;
import com.soumya.Social_Media_Project.services.PostService;
import com.soumya.Social_Media_Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    PostRepo postRepo;

    @Override
    public Comment createComment(Comment comment, Integer userId, Integer postId) throws CommentException {
        try {
            User user = userService.findUserById(userId);
            Post post = postService.findPostById(postId);
            Comment newComment = new Comment();
            newComment.setUser(user);
            newComment.setContent(comment.getContent());
            newComment.setCreatedAt(LocalDateTime.now());
            newComment.setPostId(post.getId());
            commentRepository.save(newComment);

            post.getComments().add(newComment);
            postRepo.save(post);
            return newComment;
        }catch (Exception e){
            throw new CommentException(e.getMessage());
        }
    }

    @Override
    public Comment findCommentById(Integer commentId) throws CommentException {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isEmpty()) throw new CommentException("comment not exists");
        return comment.get();
    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws CommentException {
        try {
            User user = userService.findUserById(userId);
            Comment comment = findCommentById(commentId);
            if (comment.getLiked().contains(user)){
                comment.getLiked().remove(user);//remove like
            }else {
                comment.getLiked().add(user);//add like
            }
            return commentRepository.save(comment);
        }catch (Exception e){
            throw new CommentException(e.getMessage());
        }
    }

    @Override
    public String deleteComment(Integer userId, Integer commentId) throws CommentException {
        try {
            Optional<Comment> commentById = commentRepository.findById(commentId);
            if (commentById.isEmpty()) throw new Exception("comment not found");
            Comment comment = commentById.get();
            Post post = postService.findPostById(comment.getPostId());
            User user = userService.findUserById(userId);

            if (post.getUser().getId() == userId || comment.getUser().getId() == userId){//author of that post or the owner of that comment can delete the comment
                post.getComments().remove(comment);
                postRepo.save(post);
                commentRepository.deleteById(commentId);
            }else {
                throw new Exception("you can no delete other's comment");
            }

            return "comment deleted";
        }catch (Exception e){
            throw new CommentException(e.getMessage());
        }
    }

}
