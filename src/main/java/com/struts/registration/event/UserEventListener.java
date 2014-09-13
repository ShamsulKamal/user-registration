package com.struts.registration.event;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.struts.registration.domain.User;
import com.struts.registration.domain.UserProperties;

/**
 *
 * @author Shamsul Kamal
 *
 */
public class UserEventListener implements PreInsertEventListener, PreUpdateEventListener {
    private final Logger logger = LoggerFactory.getLogger(UserEventListener.class);
    private static final long serialVersionUID = 1L;

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        logger.info(">>> onPreInsert called");
        if (event.getEntity() instanceof User) {
            Date now = new Date();
            String uuid = UUID.randomUUID().toString();

            User user = (User) event.getEntity();
            user.setUuid(uuid);
            user.setCreatedBy("creator");
            user.setCreatedDate(now);
            user.setLastUpdatedBy("creator");
            user.setLastUpdated(now);

            String[] properties = event.getPersister().getEntityMetamodel().getPropertyNames();
            List<String> propertiesList = Arrays.asList(properties);
            event.getState()[propertiesList.indexOf(UserProperties.UUID)] = uuid;
            event.getState()[propertiesList.indexOf(UserProperties.CREATEDBY)] = "creator";
            event.getState()[propertiesList.indexOf(UserProperties.CREATEDDATE)] = now;
            event.getState()[propertiesList.indexOf(UserProperties.LASTUPDATEDBY)] = "creator";
            event.getState()[propertiesList.indexOf(UserProperties.LASTUPDATED)] = now;
        }
        return false;
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        logger.info(">>> onPreUpdate called");
        if (event.getEntity() instanceof User) {
            Date now = new Date();
            User user = (User) event.getEntity();
            user.setLastUpdatedBy("updater");
            user.setLastUpdated(now);

            String[] properties = event.getPersister().getEntityMetamodel().getPropertyNames();
            List<String> propertiesList = Arrays.asList(properties);
            event.getState()[propertiesList.indexOf(UserProperties.LASTUPDATEDBY)] = "updater";
            event.getState()[propertiesList.indexOf(UserProperties.LASTUPDATED)] = now;
        }
        return false;
    }

}
