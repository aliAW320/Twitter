package com.twiter.Twiter12.Models;

import com.twiter.Twiter12.DataBase.DBSetup;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    public static String getEmailByToken(String token) {

        Session session = null;
        Transaction transaction = null;
        String email = null;
        try {
            session = DBSetup.getSession(); // Assuming DBSetup.getSession() gives you a Hibernate session
            transaction = session.beginTransaction();

            String hql = "SELECT ts.email FROM TokenSeason ts WHERE ts.token = :token";
            Query<String> query = session.createQuery(hql, String.class);
            query.setParameter("token", token);

            email = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error during getEmailByToken: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return email;
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
