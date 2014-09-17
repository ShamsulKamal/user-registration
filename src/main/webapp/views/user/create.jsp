<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el"%>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean-el"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html-el:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean-el:message key="user.create" /></title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <script type="text/javascript">
//             function saveUser() {
//                 document.forms[0].action='saveUser.do';
//                 document.forms[0].submit();
//             }
//             function listUser() {
//                 document.forms[0].action='listUser.do';
//                 document.forms[0].submit();
//             }
        </script>
    </head>
    <body>
        <html-el:errors />
        <html-el:form focus="firstName">
            <table border="0" width="100%">
                <tr>
                    <td>
                        <bean-el:message key="user.username" />
                    </td>
                    <td>
                        <html-el:text property="username" size="50" errorStyleClass="error-input" errorKey="org.apache.struts.action.ERROR" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <bean-el:message key="user.password" />
                    </td>
                    <td>
                        <html-el:password property="password" size="50" errorStyleClass="error-input" errorKey="org.apache.struts.action.ERROR"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <bean-el:message key="user.email" />
                    </td>
                    <td>
                        <html-el:text property="email" size="50" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <bean-el:message key="user.birthdate" />
                    </td>
                    <td>
                        <html-el:text property="birthdateStr" size="25" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <bean-el:message key="user.gender" />
                    </td>
                    <td>
                        <t:selectoption 
                            readonly="false" 
                            property="genderStr" 
                            defaultOption="true"
                            collections="${userForm.genders}"/>
                    </td>
                </tr>
            </table>
            <table border="0">
                <tr>
                    <td>
<%--                         <html-el:submit onclick="saveUser()"> --%>
                        <html-el:submit onclick="this.form.action='saveUser.do'">
                            <bean-el:message key="button.submit" />
                        </html-el:submit>
                    </td>
                    <td>
<%--                         <html-el:submit onclick="listUser()"> --%>
                        <html-el:submit onclick="this.form.action='listUser.do'">
                            <bean-el:message key="button.cancel" />
                        </html-el:submit>
                    </td>
                    <td>
                        <html-el:reset>
                            <bean-el:message key="button.reset" />
                        </html-el:reset>
                    </td>
                </tr>
            </table>
        </html-el:form>
    </body>
</html-el:html>