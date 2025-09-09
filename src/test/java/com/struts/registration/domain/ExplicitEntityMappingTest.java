package com.struts.registration.domain;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ExplicitEntityMappingTest {

    @Test
    public void testUserEntityMapping() {
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySetting("hibernate.connection.url", "jdbc:h2:mem:test")
                .applySetting("hibernate.connection.driver_class", "org.h2.Driver")
                .applySetting("hibernate.connection.username", "sa")
                .applySetting("hibernate.connection.password", "")
                .applySetting("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .build();
        
        try {
            SessionFactory sessionFactory = new MetadataSources(serviceRegistry)
                    .addAnnotatedClass(User.class)
                    .buildMetadata()
                    .buildSessionFactory();
            
            assertNotNull("SessionFactory should be created", sessionFactory);
            
            sessionFactory.close();
        } finally {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }
}