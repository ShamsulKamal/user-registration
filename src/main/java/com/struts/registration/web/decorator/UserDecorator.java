package com.struts.registration.web.decorator;

import org.displaytag.decorator.TableDecorator;

import com.struts.registration.domain.User;

public class UserDecorator extends TableDecorator {

    public String getFirstName() {
        User user = (User) getCurrentRowObject();

        return "<a href=\"showUser.do?id=" + user.getId() + "\">" + user.getFirstName() + "</a>";
    }
}
