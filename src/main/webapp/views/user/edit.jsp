<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="user.edit"/></title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <script type="text/javascript">
            function updateUser() {
                document.forms[0].action='updateUser.do';
                document.forms[0].submit();
            }
            function listUser() {
                document.forms[0].action='listUser.do';
                document.forms[0].submit();
            }
        </script>
    </head>
    <body>
        <html:errors />
        <html:form>
            <html-el:hidden property="id"/>
            <html-el:hidden property="uuid"/>
            <table border="0" width="100%">
                <tr>
                    <th>
                        <bean:message key="user.username" />
                    </th>
                    <td >
                        <html-el:text property="username" size="50" titleKey="user.username" errorStyleClass="error-input" errorKey="org.apache.struts.action.ERROR" />
                    </td>
                </tr>
                <tr>
                    <th>
                        <bean:message key="user.password" />
                    </th>
                    <td>
                        <html-el:password property="password" size="50" titleKey="user.password" errorStyleClass="error-input" errorKey="org.apache.struts.action.ERROR"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        <bean:message key="user.email" />
                    </th>
                    <td>
                        <html-el:text property="email" size="40" maxlength="50" titleKey="user.email" />
                    </td>
                </tr>
            </table>
            <table border="0" width="100%">
                <tr>
                    <td>
                        <html:submit onclick="updateUser()">
                            <bean:message key="button.update" />
                        </html:submit>
                        <html:submit onclick="listUser()">
                            <bean:message key="button.cancel" />
                        </html:submit>
                    </td>
                </tr>
            </table>
        </html:form>
    </body>
</html>