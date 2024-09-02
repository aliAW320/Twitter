package com.twiter.Twiter12.DataBase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBSetup {
    private static SessionFactory sessionFactory;

    public static void setup() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        if (sessionFactory == null) {
            setup();
        }
        return sessionFactory.openSession();
    }
}
