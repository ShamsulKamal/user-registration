package com.struts.registration.web.action;

import javax.servlet.ServletException;

import org.apache.struts.actions.MappingDispatchAction;

import com.struts.registration.dao.DaoFactory;
import com.struts.registration.exception.ApplicationException;

public abstract class BaseAction extends MappingDispatchAction {

    protected DaoFactory getDaoFactory() {
        DaoFactory daoFactory;
        String daoFactoryName = getServlet().getInitParameter("daoFactoryClass");
        try {
            if (daoFactoryName != null) {
                Class<?> daoFactoryClass = Class.forName(daoFactoryName);
                daoFactory = DaoFactory.instance(daoFactoryClass);
            } else {
                throw new ServletException("Configure servlet with a daoFactoryClass parameter!");
            }
        } catch (Exception ex) {
            throw new ApplicationException("Can't find DaoFactory: " + daoFactoryName, ex);
        }
        return daoFactory;
    }
}
