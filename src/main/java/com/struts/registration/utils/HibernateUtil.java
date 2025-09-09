package com.struts.registration.utils;

import com.struts.registration.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Shamsul Kamal
 *
 */
public class HibernateUtil {
    private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
    private static final SessionFactory SESSION_FACTORY;
    private static final StandardServiceRegistry SERVICE_REGISTRY;
//    private static final ThreadLocal<Session> localSession = new ThreadLocal<Session>();

    private HibernateUtil() {
    }

    static {
        try {
            // Use Java configuration instead of hibernate.cfg.xml
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
            configuration.setProperty(Environment.HBM2DDL_AUTO, "update");
            configuration.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            
            // Cache settings (using correct property names)
            configuration.setProperty(Environment.USE_SECOND_LEVEL_CACHE, "false");
            configuration.setProperty(Environment.USE_QUERY_CACHE, "false");
            configuration.setProperty(Environment.GENERATE_STATISTICS, "false");
            
            // Add entity mappings
            configuration.addAnnotatedClass(User.class);
            
            SERVICE_REGISTRY = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
            
            SESSION_FACTORY = configuration.buildSessionFactory(SERVICE_REGISTRY);
        } catch (Throwable t) {
            logger.error("Initial SessionFactory creation failed.", t);
            throw new ExceptionInInitializerError(t);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void shutdown() {
       getSessionFactory().close();
       StandardServiceRegistryBuilder.destroy(SERVICE_REGISTRY);
    }

}
