package com.struts.registration.domain;

import org.junit.Test;

public class UserTest {

    @Test
    public void testToString() {
        User user = new User();
        user.setFirstName("Shamsul");
        System.out.println(user);
    }
}
