package com.soumya.Social_Media_Project.services;

import com.soumya.Social_Media_Project.Exceptions.MessageException;
import com.soumya.Social_Media_Project.models.Message;
import com.soumya.Social_Media_Project.models.User;

import java.util.List;

public interface MessageService {
    Message createMessage(User user, Integer chatId, Message message) throws MessageException;
    List<Message> findChatsMessages(Integer chatId) throws MessageException;
}
