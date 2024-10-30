<%-- Document : Search Created on : Oct 18, 2024, 8:37:25 AM Author : hoangnn --%>
<%@page import="Models.DTO.Order"%>
<%@page import="Models.DAO.OrderDAO"%>
<%@page import="Models.DTO.Product" %>
<%@page import="Models.DAO.ProductDAO" %>
<%@page import="Models.DTO.Supplier" %>
<%@page import="Models.DAO.SupplierDAO" %>
<%@page import="Models.DTO.Category" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.ArrayList" %>
<%@page import="Models.DAO.CategoryDAO" %>
<%@page import="Models.DTO.User" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body>
        <jsp:include page="Header.jsp" />
        <c:set var="accountLoggedIn" value="${accountLoggedIn}" />
        <!--welcome-->
        <c:if test="${accountLoggedIn!=null}">
            <c:set var="lastName" value="${accountLoggedIn.fullName}" />
        </c:if>
        <h3 style="color: red">Welcome ${lastName}</h3>

        <%  OrderDAO dao = new OrderDAO();
            ArrayList<Order> orderList = dao.getAll();
            request.setAttribute("orderList", orderList);
        %>

        <c:set var="list" value="${orderList}" />
        <c:set var="count" value="1" />
        <c:if test="${list!= null}">
            <table border="1" class="table table-hover">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>orderID</th>
                        <th>customerID</th>
                        <th>orderDate</th>
                        <th>shipAddress</th>
                        <th>status</th>
                        <th colspan="2">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${orderList}">
                        <tr>
                            <td>${count}</td>
                            <td>${item.orderID}</td>
                            <td>${item.customerID}</td>
                            <td>${item.orderDate}</td>
                            <td>${item.shipAddress}</td>
                            <td>${item.status}</td>
                            <td>
                                <a href="OrderDeleteController?action=Delete&txtOrderID=${item.orderID}">Delete</a>
                            </td>
                            <td>
                                <a href="OrderDetailController?action=Details&txtOrderID=${item.orderID}">View</a>
                            </td>
                        </tr>
                        <c:set var="count" value="${count+1}" />
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:set var="message" value="${message}"/>
        <c:if test="${message != null}">
            <p style="color: green">${message}</p>
        </c:if>

        <h3>Number of Category: ${(orderList != null)?orderList.size():0}</h3>
    </body>

</html>