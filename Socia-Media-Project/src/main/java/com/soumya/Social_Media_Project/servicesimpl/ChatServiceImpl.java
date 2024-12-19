package com.soumya.Social_Media_Project.servicesimpl;

import com.soumya.Social_Media_Project.Exceptions.ChatException;
import com.soumya.Social_Media_Project.models.Chat;
import com.soumya.Social_Media_Project.models.User;
import com.soumya.Social_Media_Project.repo.ChatRepository;
import com.soumya.Social_Media_Project.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatRepository chatRepository;

    @Override
    public Chat createChat(User reqUser, User user2) {
        Chat isExists = chatRepository.findChatByUsersId(reqUser,user2);
        if (isExists != null){
            return isExists;
        }

        Chat newChat = new Chat();
        newChat.getUsers().add(user2);
        newChat.getUsers().add(reqUser);
        newChat.setCreatedAt(LocalDateTime.now());
        return chatRepository.save(newChat);
    }

    @Override
    public Chat findChatById(Integer chatId) throws ChatException {
        Optional<Chat> chatById = chatRepository.findById(chatId);
        if (chatById.isEmpty()) throw new ChatException("chat not exists with this id - "+chatId);
        return chatById.get();
    }

    @Override
    public List<Chat> findUsersChat(Integer userId) {
        return chatRepository.findByUsersId(userId);
    }

    @Override
    public String deleteChat(Integer chatId, User user) throws ChatException {
        Optional<Chat> chatById = chatRepository.findById(chatId);
        if (chatById.isEmpty()) {
            throw new ChatException("chat not found");
        }
        Chat chat = chatById.get();
        if (!chat.getUsers().contains(user)){
            throw new ChatException("you can not delete this chat");
        }

        chatRepository.delete(chat);
        return "chat deleted successfully";
    }
}
