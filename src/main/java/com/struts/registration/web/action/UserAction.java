package com.struts.registration.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.struts.registration.dao.UserDao;
import com.struts.registration.domain.User;
import com.struts.registration.web.form.UserForm;

/**
 *
 * @author Shamsul Kamal
 *
 */
public class UserAction extends BaseAction {
    private final Logger logger = LoggerFactory.getLogger(UserAction.class);

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
        UserDao userDao = getDaoFactory().getUserDao();
        List<User> users = userDao.findAll();
        request.setAttribute("users", users);
        return mapping.findForward("success");
    }

    public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {

        return mapping.findForward("success");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {

        UserForm userForm = (UserForm) form;

        User user = new User();
        PropertyUtils.copyProperties(user, userForm);

        UserDao userDao = getDaoFactory().getUserDao();
        userDao.save(user);

        return mapping.findForward("success");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
        return mapping.findForward("success");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
        return mapping.findForward("success");
    }

    public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
        return mapping.findForward("success");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
        return mapping.findForward("success");
    }

}
