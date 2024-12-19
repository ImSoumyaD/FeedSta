package com.soumya.Social_Media_Project.services;

import com.soumya.Social_Media_Project.Exceptions.ChatException;
import com.soumya.Social_Media_Project.models.Chat;
import com.soumya.Social_Media_Project.models.User;

import java.util.List;

public interface ChatService {
    Chat createChat(User reqUser, User userId2);
    Chat findChatById(Integer chatId) throws ChatException;
    List<Chat> findUsersChat(Integer userId);
    String deleteChat(Integer chatId, User userId) throws ChatException;
}
