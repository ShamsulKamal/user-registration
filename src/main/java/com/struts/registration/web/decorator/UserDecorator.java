package com.struts.registration.web.decorator;

import org.displaytag.decorator.TableDecorator;

import com.struts.registration.domain.User;

public class UserDecorator extends TableDecorator {

    public String getUsername() {
        User user = (User) getCurrentRowObject();
     // FIXME: user UserProperties
        String url = "<a href=\"showUser.do?id=" +
                        user.getId() +
                        "&uuid=" +
                        user.getUuid() +
                        "\">" +
                        user.getUsername() + "</a>";

        return url;
    }

    public String getDocumentPath() {
        User user = (User) getCurrentRowObject();
        // FIXME: user UserProperties
        String url = "<a href=\"download.do?id=" +
                        user.getId() +
                        "&uuid=" +
                        user.getUuid() +
                        "\">" +
                        user.getDocumentPath() + "</a>";
        return url;
    }
}
