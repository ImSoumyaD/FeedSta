package com.soumya.Social_Media_Project.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String image;
    private String video;
    private String caption;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    private LocalDateTime createdAt;
    @ManyToMany
    private List<User> liked = new ArrayList<>();
    @OneToMany
    private List<Comment> comments = new ArrayList<>();

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Integer id;
//    private String image;
//    private String video;
//    private String caption;
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//    private LocalDateTime createdAt;
//    @ManyToMany
//    @JoinTable(
//            name = "post_likes",
//            joinColumns = @JoinColumn(name = "post_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private List<User> liked = new ArrayList<>();
//    @ManyToMany(mappedBy = "savedPost", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<User> savedByUsers = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getLiked() {
        return liked;
    }

    public void setLiked(List<User> liked) {
        this.liked = liked;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
