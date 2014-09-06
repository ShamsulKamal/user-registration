package com.struts.registration.utils;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY;
    private static final ServiceRegistry SERVICE_REGISTRY;
    private static final ThreadLocal<Session> localSession = new ThreadLocal<Session>();

    private HibernateUtil() {
    }

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            Properties properties = configuration.getProperties();
            SERVICE_REGISTRY = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
            SESSION_FACTORY = configuration.buildSessionFactory(SERVICE_REGISTRY);
        } catch (Throwable t) {
            System.err.println("Initial SessionFactory creation failed." + t);
            throw new ExceptionInInitializerError(t);
        }
    }

    public static Session currentSession() throws HibernateException {
        Session session = localSession.get();
        // Open a new Session, if this Thread has no session
        if (session == null) {
            session = SESSION_FACTORY.openSession();
            localSession.set(session);
        }
        return session;
    }

    public static void closeSession() throws HibernateException {
        Session session = localSession.get();
        localSession.set(null);
        if (session != null) {
            session.close();
        }
    }

}
