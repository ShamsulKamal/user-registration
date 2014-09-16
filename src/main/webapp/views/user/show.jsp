<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="user.show"/></title>
        
        <script type="text/javascript">
            function editUser() {
                document.forms[0].action='editUser.do';
                document.forms[0].submit();
            }
            function deleteUser() {
                document.forms[0].action='deleteUser.do';
                document.forms[0].submit();
            }
            function listUser() {
                document.forms[0].action='listUser.do';
                document.forms[0].submit();
            }
        </script>
    </head>
    <body>
        <html:form>
            <html-el:hidden property="id"/>
            <html-el:hidden property="uuid"/>
            <table border="0" width="100%">
                <tr>
                    <th>
                        <bean:message key="user.username" />
                    </th>
                    <td >
                        <html-el:text property="username" size="40" readonly="true" maxlength="50" titleKey="user.username" />
                    </td>
                </tr>
                <tr>
                    <th>
                        <bean:message key="user.password" />
                    </th>
                    <td>
                        <html-el:password property="password" size="40" readonly="true" maxlength="50" titleKey="user.password" />
                    </td>
                </tr>
                <tr>
                    <th>
                        <bean:message key="user.email" />
                    </th>
                    <td>
                        <html-el:text property="email" size="40" readonly="true" maxlength="50" titleKey="user.email" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <bean:message key="user.birthdate" />
                    </td>
                    <td>
                        <html:text property="birthdateStr" size="25" readonly="true" />
                    </td>
                </tr>
            </table>
            <table border="0" width="100%">
                <tr>
                    <td>
                        <html:submit onclick="editUser()">
                            <bean:message key="button.edit" />
                        </html:submit>
                        <html:submit onclick="deleteUser()">
                            <bean:message key="button.delete" />
                        </html:submit>
                        <html:submit onclick="listUser()">
                            <bean:message key="button.back" />
                        </html:submit>
                    </td>
                </tr>
            </table>
        </html:form>
    </body>
</html>