<%-- Document : categoryDetails Created on : Oct 18, 2024, 8:37:50 AM Author : hoangnn --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Models.DTO.User" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body>
        <jsp:include page="Header.jsp" />
        <form method="post">
            <input type="submit" formaction="LogoutController" value="Logout" /><br />
        </form>

        <!--ton tai account-->
        <c:if test="${categoryDetails != null}">
            <h1>Category Details</h1>
            <c:set var="message" value="${requestScope.message}" />
            <c:if test="${message != null}">
                <text style="color: red">${message}</text>
            </c:if>

            <form action="CategoryUpdateController" method="post">
                <c:set var="error" value="${requestScope.errorDetail}" />

                categoryName
                <input type="text" name="txtCategoryName" value="${categoryDetails.categoryName}" />
                <c:if test="${not empty error.categoryNameError}">
                    <text style="color: red">${error.categoryNameError}</text>
                </c:if><br />

                description
                <input type="text" name="txtDescription" value="${categoryDetails.description}" />
                <c:if test="${not empty error.descriptionError}">
                    <text style="color: red">${error.descriptionError}</text>
                </c:if><br />

                <input type="submit" value="Update" name="action" />
            </form>

            <a href="CategoryView.jsp">Back</a><br />

        </c:if>

    </body>

</html>