package com.soumya.Social_Media_Project.controller;

import com.soumya.Social_Media_Project.Exceptions.ChatException;
import com.soumya.Social_Media_Project.models.Chat;
import com.soumya.Social_Media_Project.models.User;
import com.soumya.Social_Media_Project.services.ChatService;
import com.soumya.Social_Media_Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {
    @Autowired
    ChatService chatService;
    @Autowired
    UserService userService;

    @PostMapping("/create/{userId2}")
    public Chat createChat(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId2) throws ChatException {
        try {
            User reqUser = userService.getUserFromJwt(jwt);
            if (reqUser.getId() == userId2) throw new Exception("you can not create a chat with yourself");
            User user2 = userService.findUserById(userId2);
            Chat chat = chatService.createChat(reqUser, user2);
            //giving password field null value for security
            for (User user : chat.getUsers()){
                user.setPassword(null);
            }
            return chat;
        }catch (Exception e){
            throw new ChatException(e.getMessage());
        }
    }

    @GetMapping
    public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) throws ChatException {
        try {
            User reqUser = userService.getUserFromJwt(jwt);
            return chatService.findUsersChat(reqUser.getId());
        }catch (Exception e){
            throw new ChatException(e.getMessage());
        }
    }

    @GetMapping("/find/{chatId}")
    public Chat findChatById(@PathVariable Integer chatId) throws ChatException {
        return chatService.findChatById(chatId);
    }

    @DeleteMapping("/delete/{chatId}")
    public String deleteChat(@RequestHeader("Authorization") String jwt,@PathVariable Integer chatId) throws ChatException {
        try {
            User user = userService.getUserFromJwt(jwt);
            return chatService.deleteChat(chatId,user);
        }catch (Exception e){
            throw new ChatException(e.getMessage());
        }
    }
}
