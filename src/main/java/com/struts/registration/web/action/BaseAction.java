package com.struts.registration.web.action;

import org.apache.struts.actions.MappingDispatchAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.struts.registration.dao.DaoFactory;
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
//        String daoFactoryName = DaoFactoryManager.class.getName();
        try {
//            Class<?> daoFactoryClass = Class.forName(daoFactoryName);
            daoFactory = DaoFactory.instance(DaoFactory.FACTORY);
        } catch (Exception ex) {
            String msg = "Can't find DaoFactory: " + DaoFactory.FACTORY.getName();
            logger.error(msg);
            throw new ApplicationException(msg, ex);
        }
    }

    protected UserDao getUserDao() {
        return daoFactory.getUserDao();
    }
}
