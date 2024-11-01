<%-- Document : accountDetails Created on : Oct 18, 2024, 8:37:50 AM Author : hoangnn --%>
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
        <div class="container">
            <c:set var="accountLoggedIn" value="${accountLoggedIn}" />
            <c:set var="searchValue" value="${param.txtSearchValue}" />

            <!--ton tai account-->
            <c:if test="${accountDetails != null}">
                <h1>Account Details</h1>
                <c:set var="message" value="${requestScope.message}" />
                <c:if test="${message != null}">
                    <p style="color: red">${message}</p>
                </c:if>

                <form action="AccountUpdateController" method="post">
                    <c:set var="error" value="${requestScope.errorDetail}" />

                    UserName
                    <input type="text" name="txtUserName" value="${accountDetails.userName}" />
                    <c:if test="${not empty error.userNameError}">
                        <p style="color: red">${error.userNameError}</p>
                    </c:if><br />

                    Password
                    <input type="password" placeholder="Enter password" name="txtPassword" />
                    <c:if test="${not empty error.passwordError}">
                        <p style="color: red">${error.passwordError}</p>
                    </c:if><br />

                    FullName
                    <input type="text" placeholder="Enter full name" name="txtFullName" value="${accountDetails.fullName}" />
                    <c:if test="${not empty error.fullNameError}">
                        <p style="color: red">${error.fullNameError}</p>
                    </c:if><br />

                    <c:set value="${accountDetails.type == 'Staff'}" var="isTypeStaff" />
                    <c:set value="${accountLoggedIn.type != 'Staff'}" var="currentAccountIsNotStaff" />
                    <input type="checkbox" name="chkIsStaff" checked="${isTypeStaff}" disabled="${currentAccountIsNotStaff}"/>isStaff<br />

                    <input type="submit" value="Update" name="action" />
                </form>

                <input type="hidden" value="${searchValue}" name="txtSearchValue" />
                <c:if test="${accountLoggedIn.type=='Staff'}">
                    <a href="AccountSearchController?action=Search&txtSearchValue=${searchValue}">Back</a><br />
                </c:if>
                <c:if test="${!accountLoggedIn.type=='Staff'}">
                    <a href="Login.jsp">Back</a><br />
                </c:if>
            </c:if>
        </div>

    </body>
</html>