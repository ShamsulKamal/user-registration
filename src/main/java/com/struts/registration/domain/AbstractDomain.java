package com.struts.registration.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public abstract class AbstractDomain implements Serializable {
    private static final long serialVersionUID = 313874549972903559L;

    @Override
    public String toString() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
        builder.setAppendStatics(false);
        builder.setAppendTransients(true);
        return builder.toString();
    }

}
