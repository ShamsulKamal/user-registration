package com.struts.registration.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User extends AbstractDomain implements Identifiable {//, Auditable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

//    private Date createdDate;
//    private String createdBy;
//    private Date lastUpdated;
//    private String lastUpdatedBy;

    @Column(name="FIRSTNAME")
    private String firstName;
    @Column(name="LASTNAME")
    private String lastName;
//    private String email;
//    private Date birthdate;

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

//    @Override
//    public void setCreatedBy(String createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    @Override
//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    @Override
//    public void setCreatedDate(Date createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    @Override
//    public Date getCreatedDate() {
//        return createdDate;
//    }
//
//    @Override
//    public void setLastUpdatedBy(String updatedBy) {
//        this.lastUpdatedBy = updatedBy;
//
//    }
//
//    @Override
//    public String getLastUpdatedBy() {
//        return lastUpdatedBy;
//    }
//
//    @Override
//    public void setLastUpdated(Date updateDate) {
//        this.lastUpdated = updateDate;
//    }
//
//    @Override
//    public Date getLastUpdated() {
//        return lastUpdated;
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Date getBirthdate() {
//        return birthdate;
//    }
//
//    public void setBirthdate(Date birthdate) {
//        this.birthdate = birthdate;
//    }
}
