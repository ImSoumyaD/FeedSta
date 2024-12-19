package com.soumya.Social_Media_Project.controller;

import com.soumya.Social_Media_Project.Exceptions.MessageException;
import com.soumya.Social_Media_Project.models.Message;
import com.soumya.Social_Media_Project.models.User;
import com.soumya.Social_Media_Project.services.MessageService;
import com.soumya.Social_Media_Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;

    @PostMapping("/create/chat/{chatId}")
    public Message createMessage(@RequestHeader("Authorization") String jwt, @PathVariable Integer chatId, @RequestBody Message message) throws MessageException {
        try {
            User user = userService.getUserFromJwt(jwt);
            return messageService.createMessage(user,chatId,message);
        }catch (Exception e){
            throw new MessageException(e.getMessage());
        }
    }

    @GetMapping("/chat/{chatId}")
    public List<Message> getChatMessages(@PathVariable Integer chatId) throws MessageException {
        return messageService.findChatsMessages(chatId);
    }
}
