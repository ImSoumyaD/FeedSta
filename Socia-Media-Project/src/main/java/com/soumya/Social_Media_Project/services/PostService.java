package com.soumya.Social_Media_Project.services;

import com.soumya.Social_Media_Project.Exceptions.PostException;
import com.soumya.Social_Media_Project.models.Post;

import java.util.List;

public interface PostService {
    Post findPostById(Integer postId) throws PostException;
    Post createNewPost(Post post, Integer userId) throws PostException;
    String deletePost(Integer postId, Integer userId) throws PostException;
    List<Post> findPostByUserId(Integer userId) throws PostException;
    List<Post> finsAllPost();
    Post likePost(Integer postId, Integer userId) throws PostException;
}
