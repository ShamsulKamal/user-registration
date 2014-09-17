package com.struts.registration.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="USER")
public class User extends AbstractDomain implements Identifiable, Auditable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    @Column(name = "UUID", nullable = false, length = 36, unique = true)
    private String uuid;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATEDDATE")
    private Date createdDate;
    @Column(name="CREATEDBY", length=50)
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LASTUPDATED")
    private Date lastUpdated;
    @Column(name="LASTUPDATEDBY")
    private String lastUpdatedBy;

    @Column(name = "USERNAME", nullable = false, length = 50, unique = true)
    private String username;
    @Column(name="PASSWORD")
    @NotNull
    private String password;
    @Column(name = "EMAIL", length = 100)
    @Email
    private String email;
    @Column(name = "BDATE")
    @Type(type = "date")
    private Date birthdate;
    @Column(name="GENDER")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if ((this.id != null) && !this.id.equals(id)) {
            throw new IllegalArgumentException("Not allowed to change the id property.");
        }
        this.id = id;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setLastUpdatedBy(String updatedBy) {
        this.lastUpdatedBy = updatedBy;

    }

    @Override
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    @Override
    public void setLastUpdated(Date updateDate) {
        this.lastUpdated = updateDate;
    }

    @Override
    public Date getLastUpdated() {
        return lastUpdated;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Object getKey() {
        return getUuid();
    }
}
