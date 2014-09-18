package com.struts.registration.domain;

import java.io.Serializable;

public enum Gender implements Serializable {
    MALE, FEMALE;

    public String getName() {
        return name();
    }
}
