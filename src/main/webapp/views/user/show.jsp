<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean-el"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean-el:message key="user.show"/></title>
        
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
        <html-el:form>
            <html-el:hidden property="id"/>
            <html-el:hidden property="uuid"/>
            <table border="0" width="100%">
                <tr>
                    <td>
                        <bean-el:message key="user.username" />
                    </td>
                    <td >
                        <html-el:text property="username" size="40" readonly="true" maxlength="50" titleKey="user.username" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <bean-el:message key="user.password" />
                    </td>
                    <td>
                        <html-el:password property="password" size="40" readonly="true" maxlength="50" titleKey="user.password" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <bean-el:message key="user.email" />
                    </td>
                    <td>
                        <html-el:text property="email" size="40" readonly="true" maxlength="50" titleKey="user.email" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <bean-el:message key="user.birthdate" />
                    </td>
                    <td>
                        <html-el:text property="birthdateStr" size="25" readonly="true" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <bean-el:message key="user.gender" />
                    </td>
                    <td>
                        <t:selectoption readonly="true" property="genderStr"/>
<%--                         <html-el:select property="genderStr" disabled="true"> --%>
<%--                             <html-el:optionsCollection property="genders" label="label" value="value"/> --%>
<%--                         </html-el:select> --%>
                    </td>
                </tr>
            </table>
            <table border="0" width="100%">
                <tr>
                    <td>
                        <html-el:submit onclick="editUser()">
                            <bean-el:message key="button.edit" />
                        </html-el:submit>
                        <html-el:submit onclick="deleteUser()">
                            <bean-el:message key="button.delete" />
                        </html-el:submit>
                        <html-el:submit onclick="listUser()">
                            <bean-el:message key="button.back" />
                        </html-el:submit>
                    </td>
                </tr>
            </table>
        </html-el:form>
    </body>
</html>