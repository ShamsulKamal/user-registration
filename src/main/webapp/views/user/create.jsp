<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="user.create" /></title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <script type="text/javascript">
            function saveUser() {
                document.forms[0].action='saveUser.do';
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
        <html:form focus="firstName">
            <table border="0" width="100%">
                <tr>
                    <td>
                        <bean:message key="user.username" />
                    </td>
                    <td>
                        <html:text property="username" size="50" errorStyleClass="error-input" errorKey="org.apache.struts.action.ERROR" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <bean:message key="user.password" />
                    </td>
                    <td>
                        <html:password property="password" size="50" errorStyleClass="error-input" errorKey="org.apache.struts.action.ERROR"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <bean:message key="user.email" />
                    </td>
                    <td>
                        <html:text property="email" size="50" />
                    </td>
                </tr>
<!--                 <tr> -->
<!--                     <td class="right"> -->
<%--                         <bean:message key="user.birthdate" /> --%>
<!--                     </td> -->
<!--                     <td class="left"> -->
<%--                         <html:text property="birthdate" size="50" /> --%>
<!--                     </td> -->
<!--                 </tr> -->
            </table>
            <table border="0">
                <tr>
                    <td class="right">
                        <html:submit onclick="saveUser()">
                            <bean:message key="button.submit" />
                        </html:submit>
                    </td>
                    <td class="right">
                        <html:submit onclick="listUser()">
                            <bean:message key="button.cancel" />
                        </html:submit>
                    </td>
                    <td class="right">
                        <html:reset>
                            <bean:message key="button.reset" />
                        </html:reset>
                    </td>
                </tr>
            </table>
        </html:form>
    </body>
</html:html>