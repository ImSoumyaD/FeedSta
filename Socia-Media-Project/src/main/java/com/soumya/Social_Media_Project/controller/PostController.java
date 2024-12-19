package com.soumya.Social_Media_Project.controller;

import com.soumya.Social_Media_Project.Exceptions.PostException;
import com.soumya.Social_Media_Project.models.Post;
import com.soumya.Social_Media_Project.models.User;
import com.soumya.Social_Media_Project.response.ApiResponse;
import com.soumya.Social_Media_Project.servicesimpl.PostServiceImpl;
import com.soumya.Social_Media_Project.servicesimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    PostServiceImpl postService;
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/posts/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestHeader("Authorization") String jwt) throws PostException {
        try {
            User user = userService.getUserFromJwt(jwt);
            Post newPost = postService.createNewPost(post,user.getId());
            return new ResponseEntity<>(newPost, HttpStatus.ACCEPTED);
        }catch (Exception e){
            throw new PostException(e.getMessage());
        }
    }

    @DeleteMapping("/posts/delete/{postId}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId,@RequestHeader("Authorization") String jwt) throws PostException {
        try {
            User user = userService.getUserFromJwt(jwt);
            String massage = postService.deletePost(postId, user.getId());
            ApiResponse response= new ApiResponse(massage,true);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            throw new PostException(e.getMessage());
        }
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable Integer postId) throws PostException {
        Post postById = postService.findPostById(postId);
        return new ResponseEntity<>(postById,HttpStatus.OK);
    }

    @GetMapping("/posts/all")
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> allPost = postService.finsAllPost();
        return new ResponseEntity<>(allPost,HttpStatus.OK);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUserPosts(@PathVariable Integer userId) throws PostException {
        List<Post> postOfUser = postService.findPostByUserId(userId);
        return new ResponseEntity<>(postOfUser,HttpStatus.OK);
    }

    @PutMapping("/posts/like/{postId}")
    public ResponseEntity<Post> likePost(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws PostException {
        try {
            User user = userService.getUserFromJwt(jwt);
            Post post = postService.likePost(postId, user.getId());
            return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
        }catch (Exception e){
            throw new PostException(e.getMessage());
        }
    }
}
