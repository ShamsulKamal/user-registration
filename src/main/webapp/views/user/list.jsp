<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="user.list" /></title>
        <link rel="stylesheet" type="text/css" href="css/screen.css">
    </head>
    <body>
        <div>
            <html:form action="/createUser">
                <html:submit>
                    <bean:message key="user.create" />
                </html:submit>
            </html:form>
        </div>
        <display:table name="users" pagesize="10" decorator="com.struts.registration.web.decorator.UserDecorator">
            <display:column property="username"/>
<%--             <display:column property="password"/> --%>
            <display:column property="email"/>
            <display:column property="birthdate" format="{0,date,dd-MM-yyyy}"/>
            <display:column property="createdBy"/>
            <display:column property="createdDate"/>
            <display:column property="lastUpdatedBy"/>
            <display:column property="lastUpdated"/>
        </display:table>
    </body>
</html>