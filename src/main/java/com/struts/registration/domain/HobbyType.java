package com.struts.registration.domain;

import java.io.Serializable;

public enum HobbyType implements Serializable {
    TRAVELLING,
    COOKING,
    SPORT;

    public String getName() {
        return name();
    }
}
