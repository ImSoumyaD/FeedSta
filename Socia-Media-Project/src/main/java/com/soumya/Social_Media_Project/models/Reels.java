package com.soumya.Social_Media_Project.models;

import jakarta.persistence.*;

@Entity
public class Reels {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String video;
    private String title;
    @ManyToOne
    private User user;

    public Reels() {
    }

    public Reels(Integer id, String video, String title, User user) {
        this.id = id;
        this.video = video;
        this.title = title;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
