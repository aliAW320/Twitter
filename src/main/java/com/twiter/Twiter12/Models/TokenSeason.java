package com.twiter.Twiter12.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class TokenSeason {
    @Id
    private String email;
    @Column(unique = true,nullable = false)
    private String token;
    public TokenSeason() {}

    public TokenSeason(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
