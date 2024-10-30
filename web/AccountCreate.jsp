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
        <jsp:include page="Header.jsp" />
        <h1>Create Account</h1>
        <c:set var="message" value="${requestScope.message}" />
        <c:if test="${message != null}">
            ${message}
        </c:if>

        <form action="AccountCreateController" method="post">
            <c:set var="error" value="${requestScope.errorDetail}" />

            AccountName <input type="text" name="txtAccountName" value="" />
            <c:if test="${not empty error.userNameError}">
                <text style="color: red">${error.userNameError}</text>
            </c:if><br />

            Password <input type="password" placeholder="Enter password" name="txtPassword" />
            <c:if test="${not empty error.passwordError}">
                <text style="color: red">${error.passwordError}</text>
            </c:if><br />

            FullName <input type="text" placeholder="Enter full name" name="txtFullName" />
            <c:if test="${not empty error.fullNameError}">
                <text style="color: red">${error.fullNameError}</text>
            </c:if><br />

            <input type="checkbox" name="chkIsStaff" disabled />isStaff<br />
            <input type="submit" value="Create" name="action" /><br />

            <a href="Login.jsp">Back</a><br />
        </form>
    </body>

</html>