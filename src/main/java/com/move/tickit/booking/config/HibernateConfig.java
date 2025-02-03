package com.move.tickit.booking.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {
    private static final SessionFactory sessionFactory = 
    		new Configuration()
    		.configure("com/move/tickit/booking/util/hibernate.cfg.xml")
    		.buildSessionFactory();
    
    public static SessionFactory getSessionFactory() { return sessionFactory; }
}
