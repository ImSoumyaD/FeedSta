package com.soumya.Social_Media_Project.services;

import com.soumya.Social_Media_Project.Exceptions.UserException;
import com.soumya.Social_Media_Project.models.Post;
import com.soumya.Social_Media_Project.models.User;

import java.util.List;

public interface UserService {
//    User registerUser(User user) throws UserException;
    User findUserById(Integer id) throws UserException;
    User findByEmail(String email) throws UserException;
    User followUser(Integer userId1, Integer userId2) throws UserException;
    User updateUser(User user,Integer id) throws UserException;
    List<User> searchUser(String query); //firstname/email/lastname
    User savePost(Integer postId, Integer userId) throws UserException;
    List<Post> getUsersSavedPost(Integer userId) throws UserException;
    User getUserFromJwt(String jwt) throws UserException;
}
