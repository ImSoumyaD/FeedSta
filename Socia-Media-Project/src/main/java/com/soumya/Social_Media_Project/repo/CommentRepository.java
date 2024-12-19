package com.soumya.Social_Media_Project.repo;

import com.soumya.Social_Media_Project.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
