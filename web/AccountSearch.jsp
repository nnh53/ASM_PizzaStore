<%-- Document : Search Created on : Oct 18, 2024, 8:37:25 AM Author : hoangnn --%>
<%@page import="Models.DTO.User" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body>
        <c:set var="accountLoggedIn" value="${accountLoggedIn}" />
        <c:set var="searchValue" value="${param.txtSearchValue}" />

        <!--welcome-->
        <c:if test="${userLoggedIn!=null}">
            <c:set var="lastName" value="${accountLoggedIn.lastName}" />
        </c:if>
        <h3>Welcome <text style="color: red">${lastName}</text></h3>

        <form method="post">
            <input type="submit" formaction="LogoutController" value="Logout" /><br />
        </form>

        <!--search-->
        <h1>Search user by last name</h1>
        <form action="AccountSearchController" method="post">
            Enter search value <input type="text" name="txtSearchValue"
                                      value="${searchValue==null?searchValue:''}" /><br />
            <input type="submit" value="Search" name="action" />
        </form>

        <c:set var="userList" value="${searchResult}" />
        <c:set var="count" value="1" />
        <c:if test="${userList != null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>UserName</th>
                        <th>Password</th>
                        <th>FullName</th>
                        <th>Role</th>
                        <th colspan="2">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${userList}">
                        <tr>
                            <td>${count}</td>
                            <td>${user.userName}</td>
                            <td>${user.password}</td>
                            <td>${user.fullName}</td>
                            <td>${user.type}</td>
                            <td>
                                <a href="AccountDeleteController?action=Delete&userName=${user.userName}&txtSearchValue=${searchValue}">Delete</a>
                            </td>
                            <td>
                                <a href="AccountDetailController?action=Details&userName=${user.userName}&txtSearchValue=${searchValue}">View</a>
                            </td>
                        </tr>
                        <c:set var="count" value="${count+1}" />
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:set var="message" value="${requestScope.message}" />
        <c:set var="messageDelete" value="${messageDelete}" />
        <c:if test="${messageDelete != null }">
            <c:set var="message" value="${requestScope.messageDelete}" />
        </c:if>

        <c:if test="${message != null}">
            <p style="color: green">${message}</p>
        </c:if>



        <h3>Number of users: ${(searchResult != null)?searchResult.size():0}</h3>
        <c:if test="${searchResult != null}">
            <c:if test="${searchResult.size()==0 }">
                <h3>No users were found.</h3>
            </c:if>
        </c:if>
    </body>

</html>