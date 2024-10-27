<%-- Document : CreateUser Created on : Oct 18, 2024, 8:36:10 AM Author : hoangnn --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create Category</h1>
        <c:set var="message" value="${requestScope.message}" />
        <c:if test="${message != null}">
            ${message}
        </c:if>

        <form action="CategoryCreateController" method="post">
            <c:set var="error" value="${requestScope.errorDetail}" />

            CategoryName <input type="text" name="txtCategoryName" value="" />
            <c:if test="${not empty error.userNameError}">
                <text style="color: red">${error.userNameError}</text>
            </c:if><br />

            description <input type="text" name="txtDescription" />
            <c:if test="${not empty error.passwordError}">
                <text style="color: red">${error.passwordError}</text>
            </c:if><br />

            <input type="submit" value="Create" name="action" /><br />

            <a href="Home.jsp">Back</a><br />
        </form>
    </body>

</html>