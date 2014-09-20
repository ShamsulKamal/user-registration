package com.struts.registration.domain;


public class HobbyTypeState extends StringEnumUserType<HobbyType> {
    private HobbyType hobbyType;

    public HobbyTypeState() {
        super(HobbyType.class, HobbyType.values());
    }

    public HobbyType getHobbyType() {
        return hobbyType;
    }

    public void setHobbyType(HobbyType hobbyType) {
        this.hobbyType = hobbyType;
    }
}
