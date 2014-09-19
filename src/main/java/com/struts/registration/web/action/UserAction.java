package com.struts.registration.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.util.MessageResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.struts.registration.dao.UserDao;
import com.struts.registration.domain.Gender;
import com.struts.registration.domain.MaritalStatus;
import com.struts.registration.domain.User;
import com.struts.registration.domain.UserProperties;
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
        UserDao userDao = getUserDao();
        List<User> users = userDao.findAll();
        request.setAttribute("users", users);
        return mapping.findForward("success");
    }

    public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
        List<LabelValueBean> genders = new ArrayList<LabelValueBean>();
        List<LabelValueBean> maritalStatuses = new ArrayList<LabelValueBean>();

        MessageResources messageResources = getResources(request);

        for (Gender each : Gender.values()) {
            genders.add(new LabelValueBean(messageResources.getMessage(String.format("user.%s.%s", UserProperties.GENDER, each.getName())), each.getName()));
        }
        for (MaritalStatus each : MaritalStatus.values()) {
            maritalStatuses.add(new LabelValueBean(messageResources.getMessage(String.format("user.%s.%s", UserProperties.MARITALSTATUS, each.getName())), each.getName()));
        }
        UserForm userForm = (UserForm) form;
        userForm.setGenders(genders);
        userForm.setMaritalStatuses(maritalStatuses);

        return mapping.findForward("success");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {

        UserForm userForm = (UserForm) form;

        logger.debug(">>> save user form: "+ ToStringBuilder.reflectionToString(userForm, ToStringStyle.MULTI_LINE_STYLE));

        User user = new User();
        PropertyUtils.copyProperties(user, userForm);

        UserDao userDao = getUserDao();
        userDao.save(user);
        logger.info(">>> user successfully created: " + "[" + user + "]");

        return mapping.findForward("success");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
        UserForm userForm = (UserForm) form;
        User user = getUserDao().findByIdAndUuid(userForm.getId(), userForm.getUuid());
        PropertyUtils.copyProperties(userForm, user);

        MessageResources messageResources = getResources(request);
        List<LabelValueBean> genders = new ArrayList<LabelValueBean>();
        for (Gender gender : Gender.values()) {
            genders.add(new LabelValueBean(messageResources.getMessage(String.format("user.%s.%s", UserProperties.GENDER, gender.getName())), gender.getName()));
        }
        userForm.setGenders(genders);
        List<LabelValueBean> maritalStatuses = new ArrayList<LabelValueBean>();
        for (MaritalStatus each : MaritalStatus.values()) {
            maritalStatuses.add(new LabelValueBean(messageResources.getMessage(String.format("user.%s.%s", UserProperties.MARITALSTATUS, each.getName())), each.getName()));
        }
        userForm.setMaritalStatuses(maritalStatuses);

        return mapping.findForward("success");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
        UserForm userForm = (UserForm) form;
        User user = getUserDao().findByIdAndUuid(userForm.getId(), userForm.getUuid());
        PropertyUtils.copyProperties(user, userForm);
        UserDao userDao = getUserDao();
        userDao.save(user);
        return mapping.findForward("success");
    }

    public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {

        String id = request.getParameter(UserProperties.ID);
        UserDao userDao = getUserDao();
        User user = userDao.findById(Long.valueOf(id));

        UserForm userForm = (UserForm) form;
        PropertyUtils.copyProperties(userForm, user);
        MessageResources messageResources = getResources(request);

        if (user.getBirthdate() != null) {
            userForm.setBirthdateStr(new SimpleDateFormat("dd/MM/yyyy").format(user.getBirthdate()));
        }
        userForm.setGenderStr(user.getGender().getName());

        List<LabelValueBean> maritalStatuses = new ArrayList<LabelValueBean>();
        for (MaritalStatus each : MaritalStatus.values()) {
            maritalStatuses.add(new LabelValueBean(messageResources.getMessage(String.format("user.%s.%s", UserProperties.MARITALSTATUS, each.getName())), each.getName()));
        }
        userForm.setMaritalStatuses(maritalStatuses);
        userForm.setMaritalStatusStr(user.getMaritalStatus().getName());

        return mapping.findForward("success");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
        UserForm userForm = (UserForm) form;
        UserDao userDao = getUserDao();
        User user = userDao.findById(userForm.getId());
        userDao.delete(user);
        return mapping.findForward("success");
    }
}
