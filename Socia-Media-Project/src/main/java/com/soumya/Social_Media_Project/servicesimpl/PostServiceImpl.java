package com.soumya.Social_Media_Project.servicesimpl;

import com.soumya.Social_Media_Project.Exceptions.PostException;
import com.soumya.Social_Media_Project.models.Post;
import com.soumya.Social_Media_Project.models.User;
import com.soumya.Social_Media_Project.repo.PostRepo;
import com.soumya.Social_Media_Project.repo.UserRepo;
import com.soumya.Social_Media_Project.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepo postRepo;
    @Autowired
    UserRepo userRepo;

    @Override
    public Post findPostById(Integer postId) throws PostException {
        Optional<Post> post = postRepo.findById(postId);

        if (post.isEmpty()) throw new PostException("Post not found with id "+postId);

        return post.get();
    }

    @Override
    public Post createNewPost(Post post, Integer userId) throws PostException {
        Optional<User> userById = userRepo.findById(userId);
        if (userById.isEmpty()) throw new PostException("User not exists with id "+userId);

        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setVideo(post.getVideo());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setUser(userById.get());
        return postRepo.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws PostException {
        Post post = findPostById(postId);
        Optional<User> userByid = userRepo.findById(userId);
        if (userByid.isEmpty()) throw new PostException("User not exists with id "+userId);
        if (userByid.get().getId() != post.getUser().getId()) throw new PostException("This user id not authorized to delete this post");
        List<User> allUsers = userRepo.findAll();
        for (User user : allUsers){
            user.getSavedPost().remove(post);
        }
        userRepo.saveAll(allUsers);
        postRepo.delete(post);
        return "Post deleted successfully with id "+postId;
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) throws PostException {
        Optional<User> userById = userRepo.findById(userId);
        if (userById.isEmpty()) throw new PostException("User not exists with id "+userId);
        return postRepo.findPostByUserId(userId);
    }

    @Override
    public List<Post> finsAllPost() {
        return postRepo.findAll();
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws PostException {
        Post post = findPostById(postId);
        Optional<User> user = userRepo.findById(userId);
        if (post.getLiked().contains(user.get())){
            post.getLiked().remove(user.get());
        }else{
            post.getLiked().add(user.get());
        }
        return postRepo.save(post);
    }
}
