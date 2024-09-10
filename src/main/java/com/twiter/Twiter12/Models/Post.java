package com.twiter.Twiter12.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class Post {
    @Id
    @JsonIgnore
    private long id;

    @Column(length = 2000)
    private String text;

    @ManyToOne(optional = false)
    @JoinColumn
    private User author;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime postDate;

    @Column
    private String media;

    @Column(nullable = false)
    private int likesCount = 0;



    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Post() {}

    public Post(User author, String text, LocalDateTime postDate, String media) {
        this.author = author;
        this.text = text;
        this.postDate = postDate;
        this.media = media;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
