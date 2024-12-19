package com.soumya.Social_Media_Project.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String chatName;
    private String chatImage;
    @ManyToMany
    private List<User> users = new ArrayList<>();
    @OneToMany(mappedBy = "chat")
    private List<Message> messages = new ArrayList<>();
    private LocalDateTime createdAt;

    public Chat() {
    }

    public Chat(Integer id, String chatName, String chatImage, List<User> user, LocalDateTime createdAt) {
        this.id = id;
        this.chatName = chatName;
        this.chatImage = chatImage;
        this.users = user;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getChatImage() {
        return chatImage;
    }

    public void setChatImage(String chatImage) {
        this.chatImage = chatImage;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
