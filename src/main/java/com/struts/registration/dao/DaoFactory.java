package com.struts.registration.dao;

import com.struts.registration.exception.ApplicationException;

public abstract class DaoFactory {

    public static DaoFactory instance(Class<?> factory) {
        try {
            return (DaoFactory) factory.newInstance();
        } catch (Exception e) {
            throw new ApplicationException("Couldn't create DAOFactory: " + factory, e);
        }
    }

    public abstract UserDao getUserDao();
}
