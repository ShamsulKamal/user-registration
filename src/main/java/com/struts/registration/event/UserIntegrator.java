package com.struts.registration.event;

import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.metamodel.source.MetadataImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class UserIntegrator implements Integrator {

    @Override
    public void disintegrate(SessionFactoryImplementor arg0, SessionFactoryServiceRegistry arg1) {
    }

    @Override
    public void integrate(Configuration configuration, SessionFactoryImplementor sessionFactory,
                    SessionFactoryServiceRegistry serviceRegistry) {
        EventListenerRegistry eventListenerRegistry = serviceRegistry.getService(EventListenerRegistry.class);
        eventListenerRegistry.appendListeners(EventType.PRE_INSERT, new UserEventListener());
    }

    @Override
    public void integrate(MetadataImplementor arg0, SessionFactoryImplementor arg1, SessionFactoryServiceRegistry arg2) {
    }

}
