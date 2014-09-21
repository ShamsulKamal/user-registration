package com.struts.registration.web.decorator;

import org.displaytag.decorator.TableDecorator;

import com.struts.registration.domain.User;

public class UserDecorator extends TableDecorator {

    public String getUsername() {
        User user = (User) getCurrentRowObject();

        String url = "<a href=\"showUser.do?id=" +
                        user.getId() +
                        "&uuid=" +
                        user.getUuid() +
                        "\">" +
                        user.getUsername() + "</a>";

        return url;
    }
}
