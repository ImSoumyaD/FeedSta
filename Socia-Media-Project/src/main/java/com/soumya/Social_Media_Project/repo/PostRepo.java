package com.soumya.Social_Media_Project.repo;

import com.soumya.Social_Media_Project.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {
    @Query("select p from Post p where p.user.id = :userId")
    List<Post> findPostByUserId(Integer userId);
}
