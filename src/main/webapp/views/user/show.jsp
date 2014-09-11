<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="user.show"/></title>
    </head>
    <body>
        <table border="0" width="100%">
            <tr>
                <th class="right">
                    <bean:message key="user.firstName" />
                </th>
                <td class="left">
<%--                     <html:text size="50" readonly="true" property='<c:out value="${bean.firstName}"/>' /> --%>
<html-el:text property="firstName" size="3" readonly="${bean.firstName}" maxlength="5"/>
                </td>
            </tr>
<!--             <tr> -->
<!--                 <th class="right"> -->
<%--                     <bean:message key="user.lastName" /> --%>
<!--                 </th> -->
<!--                 <td class="left"> -->
<%--                     <html:text property="lastName" size="50" /> --%>
<!--                 </td> -->
<!--             </tr> -->
        </table>
    </body>
</html>