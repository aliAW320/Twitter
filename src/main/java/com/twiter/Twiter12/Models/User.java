package com.twiter.Twiter12.Models;

import com.twiter.Twiter12.DataBase.DBSetup;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.net.URL;
import java.util.List;

@Entity
public class User {
    @Id
    private long id;
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @Column
    private int followersCount;

    @Column
    private int followingCount;

    @Column(length = 1000)
    private String bio;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Post> posts;

    @ManyToMany
    private List<User> followers;

    @ManyToMany
    private List<User> following;

    @Column
    private URL profilePicture;

    public User() {
    }

    public User(String username,  String email ,String password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public static User checkUser(String email, String password) {
        System.out.println("Checking user...");
        Session session = null;
        Transaction transaction = null;
        User user = null;
        try {
            session = DBSetup.getSession();
            transaction = session.beginTransaction();

            String hql = "FROM User u WHERE u.email = :email AND u.password = :password";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            user = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error during checkUser: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    public static User checkNewUser(String email, String username) {
        Session session = null;
        Transaction transaction = null;
        User user = null;
        try {
            session = DBSetup.getSession();
            transaction = session.beginTransaction();

            String hql = "FROM User u WHERE u.email = :email OR u.username = :username";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("email", email);
            query.setParameter("username", username);

            user = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error during checkUser: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public URL getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(URL profilePicture) {
        this.profilePicture = profilePicture;
    }
}
