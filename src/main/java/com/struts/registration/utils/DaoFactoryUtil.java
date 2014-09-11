package com.struts.registration.utils;

import com.struts.registration.dao.DaoFactory;
import com.struts.registration.exception.ApplicationException;

public class DaoFactoryUtil {
    private DaoFactory daoFactory;

    private DaoFactoryUtil() {
        String daoFactoryName = "com.struts.registration.dao.DaoFactoryManager";
        try {
            Class<?> daoFactoryClass = Class.forName(daoFactoryName);
            daoFactory = DaoFactory.instance(daoFactoryClass);
        } catch (Exception ex) {
            throw new ApplicationException("Can't find DaoFactory: " + daoFactoryName, ex);
        }
    }

    private static final class DaoFactoryUtilHolder {
        private static final DaoFactoryUtil INSTANCE = new DaoFactoryUtil();
    }

    public static DaoFactoryUtil getInstance() {
        return DaoFactoryUtilHolder.INSTANCE;
    }

    public DaoFactory getDaoFactory() {
        return daoFactory;
    }
}
