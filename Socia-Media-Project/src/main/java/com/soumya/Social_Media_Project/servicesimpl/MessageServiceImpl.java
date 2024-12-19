package com.soumya.Social_Media_Project.servicesimpl;

import com.soumya.Social_Media_Project.Exceptions.MessageException;
import com.soumya.Social_Media_Project.models.Chat;
import com.soumya.Social_Media_Project.models.Message;
import com.soumya.Social_Media_Project.models.User;
import com.soumya.Social_Media_Project.repo.ChatRepository;
import com.soumya.Social_Media_Project.repo.MessageRepository;
import com.soumya.Social_Media_Project.services.ChatService;
import com.soumya.Social_Media_Project.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Message createMessage(User user, Integer chatId, Message message) throws MessageException {
        try {
            Chat chat = chatService.findChatById(chatId);

            Message newMessage = new Message();
            newMessage.setUser(user);
            newMessage.setMessage(message.getMessage());
            newMessage.setImage(message.getImage());
            newMessage.setChat(chat);
            newMessage.setTimeStamp(LocalDateTime.now());

            chat.getMessages().add(newMessage);
            chatRepository.save(chat);
            return messageRepository.save(newMessage);
        }catch (Exception e){
            throw new MessageException(e.getMessage());
        }
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws MessageException {
        try {
            chatService.findChatById(chatId); //check if chat exists or not
            return messageRepository.findByChatId(chatId);
        }catch (Exception e){
            throw new MessageException(e.getMessage());
        }
    }
}
