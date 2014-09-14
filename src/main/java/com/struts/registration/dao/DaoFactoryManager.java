package com.struts.registration.dao;

import com.struts.registration.exception.ApplicationException;

/**
 *
 * @author Shamsul Kamal
 *
 */
public class DaoFactoryManager extends DaoFactory {

    @SuppressWarnings("unchecked")
    private GenericDao<?, Long> instantiateDao(Class<?> daoClass) {
        try {
            return ((GenericDao<?, Long>) daoClass.newInstance());
        } catch (Exception e) {
            throw new ApplicationException("Can not instantiate DAO: " + daoClass, e);
        }
    }

    @Override
    public UserDao getUserDao() {
        return (UserDao) instantiateDao(UserDaoImpl.class);
    }
}
