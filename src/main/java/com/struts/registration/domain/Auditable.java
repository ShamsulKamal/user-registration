package com.struts.registration.domain;

import java.util.Date;

public interface Auditable {
    public void setCreatedBy(String createdBy);

    public String getCreatedBy();

    public void setCreatedDate(Date createdDate);

    public Date getCreatedDate();

    public void setLastUpdatedBy(String updatedBy);

    public String getLastUpdatedBy();

    public void setLastUpdated(Date updateDate);

    public Date getLastUpdated();
}
