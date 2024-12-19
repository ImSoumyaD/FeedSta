package com.soumya.Social_Media_Project.repo;

import com.soumya.Social_Media_Project.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByChatId(Integer chatId);
}
