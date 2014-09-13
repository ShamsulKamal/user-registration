package com.struts.registration.web.action;

import org.apache.struts.actions.MappingDispatchAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.struts.registration.dao.DaoFactory;
import com.struts.registration.dao.DaoFactoryManager;
import com.struts.registration.dao.UserDao;
import com.struts.registration.exception.ApplicationException;

/**
 *
 * @author Shamsul Kamal
 *
 */
public abstract class BaseAction extends MappingDispatchAction {
    private final Logger logger = LoggerFactory.getLogger(BaseAction.class);
    private DaoFactory daoFactory;

    public BaseAction() {
        String daoFactoryName = DaoFactoryManager.class.getName();
        try {
            Class<?> daoFactoryClass = Class.forName(daoFactoryName);
            daoFactory = DaoFactory.instance(daoFactoryClass);
        } catch (Exception ex) {
            throw new ApplicationException("Can't find DaoFactory: " + daoFactoryName, ex);
        }
    }

    protected UserDao getUserDao() {
        return daoFactory.getUserDao();
    }
}
