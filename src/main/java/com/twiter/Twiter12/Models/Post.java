package com.twiter.Twiter12.Models;

import jakarta.persistence.*;

import java.net.URL;
import java.util.Date;
import java.util.List;

@Entity
public class Post {
    @Id
    private long id;

    @Column(length = 2000)
    private String text;

    @ManyToOne(optional = false)
    @JoinColumn
    private User author;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;

    @Column
    private URL media;

    @Column(nullable = false)
    private int likesCount = 0;

    @Column
    private String mediaType;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Post() {}

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

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public URL getMedia() {
        return media;
    }

    public void setMedia(URL media) {
        this.media = media;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
