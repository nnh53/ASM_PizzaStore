<%-- Document : Login Created on : Oct 18, 2024, 8:36:23 AM Author : hoangnn --%>

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
        <h1>Login</h1>
        <c:set var="message" value="${requestScope.message}" />
        <c:if test="${message != null}">
            <text style="color: red">${message}</text>
        </c:if>

        <form action="LoginController" method="post">
            AccountName <input type="text" name="txtAccountName" /><br />
            Password <input type="password" name="txtPassword" /><br />
            <input type="submit" value="Login" name="action" />
            <input type="reset" value="Reset" /><br />
            <a href="AccountCreate.jsp">Click here to Sign up</a><br />
        </form>
    </body>
</html>