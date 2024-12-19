package com.soumya.Social_Media_Project.repo;

import com.soumya.Social_Media_Project.models.Chat;
import com.soumya.Social_Media_Project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    List<Chat> findByUsersId(Integer userId);

    @Query("select c from Chat c where :user Member of c.users And :reqUser Member of c.users")
    Chat findChatByUsersId(@Param("reqUser") User reqUser,@Param("user") User user);
}
