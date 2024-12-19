package com.soumya.Social_Media_Project.controller;

import com.soumya.Social_Media_Project.Exceptions.UserException;
import com.soumya.Social_Media_Project.models.Post;
import com.soumya.Social_Media_Project.models.User;
import com.soumya.Social_Media_Project.repo.UserRepo;
import com.soumya.Social_Media_Project.servicesimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserRepo userRepo;

    @GetMapping("/users/all")
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @PutMapping("/users/update")
    public User updateUser(@RequestBody User user, @RequestHeader("Authorization") String jwt) throws UserException {
        User loggedInUser = userService.getUserFromJwt(jwt);
        return userService.updateUser(user,loggedInUser.getId());
    }

    @GetMapping("/users/{id}")
    public User searchById(@PathVariable("id") Integer userId) throws UserException {
        return userService.findUserById(userId);
    }

    @GetMapping("/users/search")
    public List<User> searchUser(@RequestParam("query") String query,@RequestHeader("Authorization") String jwt) throws UserException {
        User reqUser = userService.getUserFromJwt(jwt); // logged in user
        List<User> searchList = userService.searchUser(query);
        searchList.remove(reqUser);//remove current user from search list
        return searchList;
    }

    @PutMapping("/users/follow/{userId2}")
    public User followUser(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId2) throws UserException {
        User loggedInUser = userService.getUserFromJwt(jwt);
        return userService.followUser(loggedInUser.getId(),userId2);
    }

    @PutMapping("/users/save/post/{postId}")
    public User savePost(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.getUserFromJwt(jwt);
        return userService.savePost(postId,user.getId());
    }

    @GetMapping("/users/saved/posts")
    public List<Post> getUserSavedPost(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.getUserFromJwt(jwt);
        return userService.getUsersSavedPost(user.getId());
    }

    @GetMapping("/users/profile")
    public User getUserFromJwt(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.getUserFromJwt(jwt);
        user.setPassword(null);
        return user;
    }

}
