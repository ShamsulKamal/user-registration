package com.struts.registration.web.form;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.EmailValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.struts.registration.domain.UserProperties;

/**
 *
 * @author Shamsul Kamal
 *
 */
public class UserForm extends ActionForm {
    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory.getLogger(UserForm.class);

    private Long id;
    private String uuid;
    private String username;
    private String password;
    private String email;
    private String birthdateStr;
    private Date birthdate;
    private Date createdDate;
    private String createdBy;
    private Date lastUpdated;
    private String lastUpdatedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdateStr() {
        return birthdateStr;
    }

    public void setBirthdateStr(String birthdateStr) {
        this.birthdateStr = birthdateStr;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

//    /**
//     * Resets all properties to their default values.
//     */
//    @Override
//    public void reset(ActionMapping mapping, HttpServletRequest request) {
//    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        logger.info(">>> validate call");
        ActionErrors errors = new ActionErrors();
        validateRequired(errors);
        validateFormat(errors);
        return errors;
    }

    private void validateRequired(ActionErrors errors) {
        if (StringUtils.isBlank(username)) {
            errors.add(UserProperties.USERNAME, new ActionMessage(String.format("error.%s.required", UserProperties.USERNAME)));
        }
        if (StringUtils.isBlank(password)) {
            errors.add(UserProperties.PASSWORD, new ActionMessage(String.format("error.%s.required", UserProperties.PASSWORD)));
        }
    }

    private void validateFormat(ActionErrors errors) {
        if (StringUtils.isNotBlank(email)) {
            if (!EmailValidator.getInstance().isValid(email)) {
                errors.add(UserProperties.EMAIL, new ActionMessage(String.format("error.%s.format", UserProperties.EMAIL)));
            }
        }
        if (StringUtils.isNotBlank(birthdateStr)) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            try {
                birthdate = df.parse(birthdateStr);
            } catch (ParseException e) {
                errors.add(UserProperties.BIRTHDATE, new ActionMessage(String.format("error.%s.format", UserProperties.BIRTHDATE)));
            }
        }
    }
}
