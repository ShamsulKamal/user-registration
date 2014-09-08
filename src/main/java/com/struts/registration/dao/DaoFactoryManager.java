package com.struts.registration.dao;

import com.struts.registration.exception.ApplicationException;

public class DaoFactoryManager extends DaoFactory {

    @SuppressWarnings("unchecked")
    private Dao<?, Long> instantiateDao(Class<?> daoClass) {
        try {
            return ((Dao<?, Long>) daoClass.newInstance());
        } catch (Exception e) {
            throw new ApplicationException("Can not instantiate DAO: " + daoClass, e);
        }
    }

    @Override
    public UserDao getUserDao() {
        return (UserDao) instantiateDao(UserDaoImpl.class);
    }
}
