package com.twiter.Twiter12.Response;

import com.twiter.Twiter12.Models.Post;

import java.util.List;

public class WholeProfileResponse implements Response{
    private List<Post> posts;
    private int postCount;
    private int followersCount;
    private int followingCount;
    private String username;
    private String profileImageUrl;
    private int code;

    public WholeProfileResponse(List<Post> posts, int postCount, int followersCount, int followingCount, String username, String profileImageUrl, int code) {
        this.posts = posts;
        this.postCount = postCount;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
