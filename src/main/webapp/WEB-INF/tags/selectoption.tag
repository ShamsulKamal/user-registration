<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html-el"%>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean-el"%>

<%@ attribute name="readonly" type="java.lang.Boolean" required="true" description="" %>
<%@ attribute name="property" type="java.lang.String" required="true" description="" %>
<%@ attribute name="size" type="java.lang.String" required="false" description="" %>
<%@ attribute name="titleKey" type="java.lang.String" required="false" description="" %>
<%@ attribute name="defaultOption" type="java.lang.Boolean" required="false" description="" %>
<%@ attribute name="collections" type="java.util.List" required="false" description="" %>


<c:choose>
    <c:when test="${readonly}">
         <html-el:text property="${property}" size="${size}" readonly="true" titleKey="${titleKey}" />
    </c:when>
    <c:otherwise>
        <html-el:select property="${property}">
            
            <c:if test="${defaultOption}">
                <html-el:option value=""><bean-el:message key="select.option.default"/></html-el:option>
            </c:if>
            <html-el:options collection="collections" property="value" labelProperty="label" />
        </html-el:select>
    </c:otherwise>
</c:choose>