package com.twiter.Twiter12.Models;

import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    private long id;
    @Column(length = 500)
    private String text;

    @ManyToOne(optional = false)
    @JoinColumn
    private User author;
    @ManyToOne
    private Post post;

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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
