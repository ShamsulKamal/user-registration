<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="user.create" /></title>
</head>
    <body>
        <html:errors />
        <html:form action="/user/save" focus="firstName">
            <table border="0" width="100%">
                <tr>
                    <th class="right">
                        <bean:message key="user.firstName" />
                    </th>
                    <td class="left">
                        <html:text property="firstName" size="50" />
                    </td>
                </tr>
                <tr>
                    <th class="right">
                        <bean:message key="user.lastName" />
                    </th>
                    <td class="left">
                        <html:text property="lastName" size="50" />
                    </td>
                </tr>
<!--                 <tr> -->
<!--                     <th class="right"> -->
<%--                         <bean:message key="user.email" /> --%>
<!--                     </th> -->
<!--                     <td class="left"> -->
<%--                         <html:text property="email" size="50" /> --%>
<!--                     </td> -->
<!--                 </tr> -->
<!--                 <tr> -->
<!--                     <th class="right"> -->
<%--                         <bean:message key="user.birthdate" /> --%>
<!--                     </th> -->
<!--                     <td class="left"> -->
<%--                         <html:text property="birthdate" size="50" /> --%>
<!--                     </td> -->
<!--                 </tr> -->
                <tr>
                    <td class="right">
                        <html:submit>
                            <bean:message key="button.submit" />
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