package com.soumya.Social_Media_Project.servicesimpl;

import com.soumya.Social_Media_Project.Exceptions.UserException;
import com.soumya.Social_Media_Project.models.Post;
import com.soumya.Social_Media_Project.models.User;
import com.soumya.Social_Media_Project.repo.PostRepo;
import com.soumya.Social_Media_Project.repo.UserRepo;
import com.soumya.Social_Media_Project.services.JwtProvider;
import com.soumya.Social_Media_Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    UserRepo userRepo;
    @Autowired
    PostRepo postRepo;

    @Override
    public User findUserById(Integer id) throws UserException {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()){
            throw new UserException("User not exists with id "+ id);
        }
        return user.get();
    }

    @Override
    public User findByEmail(String email) throws UserException {
        User user = userRepo.findByEmail(email);
        if (user == null){
            throw new UserException("User not exists with email "+email);
        }
        return user;
    }

    //follow | unFollow
    @Override
    public User followUser(Integer loggedInUserId, Integer userId2) throws UserException {
        User loggedInUser = findUserById(loggedInUserId);
        User user2 = findUserById(userId2);
        if (loggedInUser.getFollowing().contains(user2.getId())){
            loggedInUser.getFollowing().remove(user2.getId());
            user2.getFollowers().remove(loggedInUser.getId());
        }else{
            loggedInUser.getFollowing().add(user2.getId());
            user2.getFollowers().add(loggedInUser.getId());
        }
        userRepo.save(loggedInUser);
        userRepo.save(user2);
        return loggedInUser;
    }

    @Override
    public User updateUser(User user, Integer id) throws UserException {
        Optional<User> userById = userRepo.findById(id);
        if (userById.isEmpty()){
            throw new UserException("User not found with id "+id);
        }
        User oldUser = userById.get();

        if (user.getEmail() != null){
            if (userRepo.findByEmail(user.getEmail()) != null){
                throw new UserException("this email is already used with another account..");
            }
            else oldUser.setEmail(user.getEmail());
        }
        if (user.getFirstName() != null){
            oldUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null){
            oldUser.setLastName(user.getLastName());
        }
        if (user.getLastName() != null){
            oldUser.setLastName(user.getLastName());
        }
        if (user.getGender() != null){
            oldUser.setGender(user.getGender());
        }
        return userRepo.save(oldUser);
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepo.searchUser(query);
    }

    //save | unSave
    @Override
    public User savePost(Integer postId, Integer userId) throws UserException {
        Optional<Post> postById = postRepo.findById(postId);
        Optional<User> userById = userRepo.findById(userId);

        if (userById.isEmpty()) throw new UserException("User not found");
        User user = userById.get();
        Post post = postById.get();

        if (user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        }
        else {
            user.getSavedPost().add(post);
        }
        return userRepo.save(user);
    }
    @Override
    public List<Post> getUsersSavedPost(Integer userId) throws UserException {
        Optional<User> userById = userRepo.findById(userId);
        if (userById.isEmpty()) throw new UserException("User not found with id "+userId);

        User user = userById.get();
        List<Post> savedPost = user.getSavedPost();
        return savedPost;
    }

    @Override
    public User getUserFromJwt(String jwt) throws UserException {
        String email = JwtProvider.getEmailFromToken(jwt);
        User user = findByEmail(email);
        return user;
    }


}
