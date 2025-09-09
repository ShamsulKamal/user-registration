package com.struts.registration.domain;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SimpleHibernateTest {

    @Test
    public void testUserEntityMapping() {
        Configuration configuration = new Configuration();
        
        // Database connection properties
        configuration.setProperty(Environment.DRIVER, "org.h2.Driver");
        configuration.setProperty(Environment.URL, "jdbc:h2:mem:test");
        configuration.setProperty(Environment.USER, "sa");
        configuration.setProperty(Environment.PASS, "");
        configuration.setProperty(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        
        // Hibernate settings
        configuration.setProperty(Environment.SHOW_SQL, "true");
        configuration.setProperty(Environment.FORMAT_SQL, "true");
        configuration.setProperty(Environment.USE_SQL_COMMENTS, "true");
        configuration.setProperty(Environment.HBM2DDL_AUTO, "create-drop");
        configuration.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        
        // Cache settings
        configuration.setProperty(Environment.USE_SECOND_LEVEL_CACHE, "false");
        configuration.setProperty(Environment.USE_QUERY_CACHE, "false");
        configuration.setProperty(Environment.GENERATE_STATISTICS, "false");
        
        // Add entity mappings
        configuration.addAnnotatedClass(User.class);
        
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        
        assertNotNull("SessionFactory should be created", sessionFactory);
        
        sessionFactory.close();
    }
}