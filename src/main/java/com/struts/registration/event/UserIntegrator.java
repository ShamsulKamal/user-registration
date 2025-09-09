package com.struts.registration.event;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

/**
 *
 * @author Shamsul Kamal
 *
 */
public class UserIntegrator implements Integrator {

    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        EventListenerRegistry eventListenerRegistry = serviceRegistry.getService(EventListenerRegistry.class);
        eventListenerRegistry.appendListeners(EventType.PRE_INSERT, new UserEventListener());
        eventListenerRegistry.appendListeners(EventType.PRE_UPDATE, new UserEventListener());
    }

    @Override
    public void disintegrate(SessionFactoryImplementor arg0, SessionFactoryServiceRegistry arg1) {
    }

}
