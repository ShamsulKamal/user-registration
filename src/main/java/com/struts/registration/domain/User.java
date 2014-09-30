package com.struts.registration.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="USER")
@TypeDef(typeClass=HobbyTypeState.class, name="hobby")
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
    @Column(name="MARITALSTATUS")
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    @Type(type="hobby")
    @ElementCollection(fetch=FetchType.LAZY)
    @JoinTable(
            name="HOBBYTYPE", // ref table.
            joinColumns = { @JoinColumn(name="USER_ID") }
    )
    @Column(name="HOBBYTYPE_NAME")
    private Set<HobbyType> hobbyTypes = new HashSet<HobbyType>();
    // TODO
    // private CLOB document;
    @Column(name="COMMENT")
    private String comment;
    @Column(name="DOCUMENTPATH")
    private String documentPath;

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

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Set<HobbyType> getHobbyTypes() {
        return hobbyTypes;
    }

    public void setHobbyTypes(Set<HobbyType> hobbyTypes) {
        this.hobbyTypes= hobbyTypes;
    }

    public void addHobbyType(HobbyType hobbyElement) {
        getHobbyTypes().add(hobbyElement);
    }

    public void removeHobbyType(HobbyType hobbyElement) {
        getHobbyTypes().remove(hobbyElement);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public Object getKey() {
        return getUuid();
    }
}
