package com.struts.registration.event;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;

import com.struts.registration.domain.User;


public class UserEventListener implements PreInsertEventListener {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        if (event.getEntity() instanceof User) {
            User user = (User) event.getEntity();

            String uuid = UUID.randomUUID().toString();
            user.setUuid(uuid);

            String[] properties = event.getPersister().getEntityMetamodel().getPropertyNames();
            List<String> propertiesList = Arrays.asList(properties);
            event.getState()[propertiesList.indexOf("uuid")] = uuid;
        }
        return false;
    }
}
