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
        <div class="container">
            <h1>Login</h1>
            <c:set var="message" value="${requestScope.message}" />
            <c:if test="${message != null}">
                <text style="color: red">${message}</text>
            </c:if>

            <form action="LoginController" method="post">
                <div class="pb-3">
                    AccountName <input type="text" name="txtAccountName" /><br />
                </div>
                <div class="pb-3">
                    Password <input type="password" name="txtPassword" /><br />
                </div>
                <input type="submit" value="Login" name="action" class="btn btn-primary"/>
                <input type="reset" value="Reset" class="btn btn-primary" /><br />
                <a href="AccountCreate.jsp">Click here to Sign up</a><br />
            </form>
        </div>
    </body>
</html>