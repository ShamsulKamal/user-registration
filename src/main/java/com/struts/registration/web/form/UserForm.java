package com.struts.registration.web.form;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.EmailValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.struts.registration.domain.Gender;
import com.struts.registration.domain.HobbyType;
import com.struts.registration.domain.MaritalStatus;
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
    private Date createdDate;
    private String createdBy;
    private Date lastUpdated;
    private String lastUpdatedBy;
    private String username;
    private String password;
    private String email;
    private Date birthdate;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private String birthdateStr;
    private Set<HobbyType> hobbyTypes = new HashSet<HobbyType>();

    private String genderStr;
    private List<LabelValueBean> genderLabelValueBeans;
    private String maritalStatusStr;
    private List<LabelValueBean> maritalStatusLabelValueBeans;
    private String[] hobbyTypesStr;
    private List<LabelValueBean> hobbyTypesLabelValueBeans;

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getGenderStr() {
        return genderStr;
    }

    public void setGenderStr(String genderStr) {
        this.genderStr = genderStr;
    }

    public List<LabelValueBean> getGenderLabelValueBeans() {
        return genderLabelValueBeans;
    }

    public void setGenderLabelValueBeans(List<LabelValueBean> genderLabelValueBeans) {
        this.genderLabelValueBeans = genderLabelValueBeans;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatusStr() {
        return maritalStatusStr;
    }

    public void setMaritalStatusStr(String maritalStatusStr) {
        this.maritalStatusStr = maritalStatusStr;
    }

    public List<LabelValueBean> getMaritalStatusLabelValueBeans() {
        return maritalStatusLabelValueBeans;
    }

    public void setMaritalStatusLabelValueBeans(List<LabelValueBean> maritalStatusLabelValueBeans) {
        this.maritalStatusLabelValueBeans = maritalStatusLabelValueBeans;
    }

    public String[] getHobbyTypesStr() {
        return hobbyTypesStr;
    }

    public void setHobbyTypesStr(String[] hobbyTypesStr) {
        this.hobbyTypesStr = hobbyTypesStr;
    }

    public List<LabelValueBean> getHobbyTypesLabelValueBeans() {
        return hobbyTypesLabelValueBeans;
    }

    public void setHobbyTypesLabelValueBeans(List<LabelValueBean> hobbyTypesLabelValueBeans) {
        this.hobbyTypesLabelValueBeans = hobbyTypesLabelValueBeans;
    }

    public Set<HobbyType> getHobbyTypes() {
        return hobbyTypes;
    }

    public void setHobbyTypes(Set<HobbyType> hobbyTypes) {
        this.hobbyTypes = hobbyTypes;
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
        validateGender(errors);
        validateMaritalStatus(errors);
        validateHobbyType(errors);
        return errors;
    }

    private void validateHobbyType(ActionErrors errors) {
        HobbyType hobbyType;
        if (hobbyTypesStr.length != 0) {
            // removes all current elements
            hobbyTypes.clear();
            for (String each : hobbyTypesStr) {
                try {
                    hobbyType = HobbyType.valueOf(each);
                    hobbyTypes.add(hobbyType);
                } catch (IllegalArgumentException e) {
                    errors.add(UserProperties.HOBBYTYPES, new ActionMessage(String.format("error.%s.type", UserProperties.HOBBYTYPES)));
                }
            }
        }
    }

    private void validateMaritalStatus(ActionErrors errors) {
        if (StringUtils.isNotBlank(maritalStatusStr)) {
            try {
                maritalStatus = MaritalStatus.valueOf(maritalStatusStr);
            } catch (IllegalArgumentException e) {
                errors.add(UserProperties.MARITALSTATUS, new ActionMessage(String.format("error.%s.type", UserProperties.MARITALSTATUS)));
            }
        }
    }

    private void validateGender(ActionErrors errors) {
        if (StringUtils.isNotBlank(genderStr)) {
            try {
                gender = Gender.valueOf(genderStr);
            } catch (IllegalArgumentException e) {
                errors.add(UserProperties.USERNAME, new ActionMessage(String.format("error.%s.type", UserProperties.GENDER)));
            }
        }
    }

    private void validateRequired(ActionErrors errors) {
        if (StringUtils.isBlank(username)) {
            errors.add(UserProperties.USERNAME, new ActionMessage(String.format("error.%s.required", UserProperties.USERNAME)));
        }
        if (StringUtils.isBlank(password)) {
            errors.add(UserProperties.PASSWORD, new ActionMessage(String.format("error.%s.required", UserProperties.PASSWORD)));
        }
        if (StringUtils.isBlank(genderStr)) {
            errors.add(UserProperties.PASSWORD, new ActionMessage(String.format("error.%s.required", UserProperties.GENDER)));
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
