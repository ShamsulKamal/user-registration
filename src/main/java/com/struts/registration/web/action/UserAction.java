package com.struts.registration.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.util.MessageResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.struts.registration.dao.UserDao;
import com.struts.registration.domain.Gender;
import com.struts.registration.domain.HobbyType;
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
        List<LabelValueBean> genderLabelValueBeans = new ArrayList<LabelValueBean>();
        List<LabelValueBean> maritalStatusLabelValueBeans = new ArrayList<LabelValueBean>();
        List<LabelValueBean> hobbyTypesLabelValueBeans = new ArrayList<LabelValueBean>();

        MessageResources messageResources = getResources(request);

        for (Gender each : Gender.values()) {
            genderLabelValueBeans.add(new LabelValueBean(messageResources.getMessage(String.format("user.%s.%s", UserProperties.GENDER, each.getName())), each.getName()));
        }
        for (MaritalStatus each : MaritalStatus.values()) {
            maritalStatusLabelValueBeans.add(new LabelValueBean(messageResources.getMessage(String.format("user.%s.%s", UserProperties.MARITALSTATUS, each.getName())), each.getName()));
        }
        for (HobbyType each : HobbyType.values()) {
            hobbyTypesLabelValueBeans.add(new LabelValueBean(messageResources.getMessage(String.format("user.%s.%s", UserProperties.HOBBYTYPES, each.getName())), each.getName()));
        }

        UserForm userForm = (UserForm) form;
        userForm.setGenderLabelValueBeans(genderLabelValueBeans);
        userForm.setMaritalStatusLabelValueBeans(maritalStatusLabelValueBeans);
        userForm.setHobbyTypesLabelValueBeans(hobbyTypesLabelValueBeans);

        return mapping.findForward("success");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {

        UserForm userForm = (UserForm) form;

        FileOutputStream outputStream = null;
        String filePath = System.getProperty("java.io.tmpdir") + "/" + userForm.getResumeFile().getFileName();
        try {
            outputStream = new FileOutputStream(new File(filePath));
            outputStream.write(userForm.getResumeFile().getFileData());
        } catch (Exception e) {
            ActionErrors errors = new ActionErrors();
            errors.add("resumeFile", new ActionMessage("errors.file.save", userForm.getResumeFile().getFileName()));
            saveErrors(request, errors);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }

        logger.debug(">>> save user form: "+ ToStringBuilder.reflectionToString(userForm, ToStringStyle.MULTI_LINE_STYLE));

        User user = new User();
        PropertyUtils.copyProperties(user, userForm);

        UserDao userDao = getUserDao();

        userDao.save(user);
        logger.info(">>> user successfully created: " + "[" + user + "]");

        if(getErrors(request).isEmpty()){
            return mapping.findForward("success");
        } else {
            return mapping.getInputForward();
        }
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
        UserForm userForm = (UserForm) form;
        User user = getUserDao().findByIdAndUuid(userForm.getId(), userForm.getUuid());
        PropertyUtils.copyProperties(userForm, user);

        MessageResources messageResources = getResources(request);

        List<LabelValueBean> genderLabelValueBeans = new ArrayList<LabelValueBean>();
        for (Gender gender : Gender.values()) {
            genderLabelValueBeans.add(new LabelValueBean(messageResources.getMessage(String.format("user.%s.%s", UserProperties.GENDER, gender.getName())), gender.getName()));
        }
        userForm.setGenderLabelValueBeans(genderLabelValueBeans);

        List<LabelValueBean> maritalStatusLabelValueBeans = new ArrayList<LabelValueBean>();
        for (MaritalStatus each : MaritalStatus.values()) {
            maritalStatusLabelValueBeans.add(new LabelValueBean(messageResources.getMessage(String.format("user.%s.%s", UserProperties.MARITALSTATUS, each.getName())), each.getName()));
        }
        userForm.setMaritalStatusLabelValueBeans(maritalStatusLabelValueBeans);

        List<LabelValueBean> hobbyTypesLabelValueBeans = new ArrayList<LabelValueBean>();
        for (HobbyType each : HobbyType.values()) {
            hobbyTypesLabelValueBeans.add(new LabelValueBean(messageResources.getMessage(String.format("user.%s.%s", UserProperties.HOBBYTYPES, each.getName())), each.getName()));
        }
        userForm.setHobbyTypesLabelValueBeans(hobbyTypesLabelValueBeans);

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

        List<LabelValueBean> maritalStatusLabelValueBeans = new ArrayList<LabelValueBean>();
        for (MaritalStatus each : MaritalStatus.values()) {
            maritalStatusLabelValueBeans.add(new LabelValueBean(messageResources.getMessage(String.format("user.%s.%s", UserProperties.MARITALSTATUS, each.getName())), each.getName()));
        }
        userForm.setMaritalStatusLabelValueBeans(maritalStatusLabelValueBeans);
        userForm.setMaritalStatusStr(user.getMaritalStatus().getName());

        List<LabelValueBean> hobbyTypesLabelValueBeans = new ArrayList<LabelValueBean>();
        for (HobbyType each : HobbyType.values()) {
            hobbyTypesLabelValueBeans.add(new LabelValueBean(messageResources.getMessage(String.format("user.%s.%s", UserProperties.HOBBYTYPES, each.getName())), each.getName()));
        }
        userForm.setHobbyTypesLabelValueBeans(hobbyTypesLabelValueBeans);

        String[] hobbyTypesStr = new String[user.getHobbyTypes().size()];
        int i = 0;
        for (HobbyType each : user.getHobbyTypes()) {
            hobbyTypesStr[i] = each.getName();
            i++;
        }
        userForm.setHobbyTypesStr(hobbyTypesStr);

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
