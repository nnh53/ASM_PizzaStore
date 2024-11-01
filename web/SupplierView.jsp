<%-- Document : Search Created on : Oct 18, 2024, 8:37:25 AM Author : hoangnn --%>
<%@page import="Models.DTO.Supplier"%>
<%@page import="Models.DAO.SupplierDAO"%>
<%@page import="Models.DTO.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.DAO.CategoryDAO"%>
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
        <jsp:include page="Header.jsp" />
        <div class="container">
            <!--set variable-->
            <c:set var="accountLoggedIn" value="${accountLoggedIn}" />
            <c:if test="${accountLoggedIn!=null}">
                <c:set var="fullName" value="${accountLoggedIn.fullName}" />
                <c:set var="isStaff" value="${(accountLoggedIn.type =='Staff')? true:false}" />
            </c:if>

            <%
                SupplierDAO dao = new SupplierDAO();
                ArrayList<Supplier> supplierList = dao.getAll();
                request.setAttribute("supplierList", supplierList);
            %>

            <c:set var="userList" value="${supplierList}" />
            <c:set var="count" value="1" />
            <c:if test="${userList != null}">
                <table border="1" class="table table-hover">
                    <thead>
                        <tr>
                            <th>No.</th>
                                <c:if test="${isStaff}">
                                <th>supplierID</th>
                                </c:if>
                            <th>companyName</th>
                            <th>address</th>
                            <th>phone</th>
                            <th colspan="2">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="supp" items="${supplierList}">
                            <tr>
                                <td>${count}</td>
                                <c:if test="${isStaff}">
                                    <td>${supp.supplierID}</td>
                                </c:if>
                                <td>${supp.companyName}</td>
                                <td>${supp.address}</td>
                                <td>${supp.phone}</td>
                                <td>
                                    <a href="SupplierDeleteController?action=Delete&txtCompanyName=${supp.companyName}">Delete</a>
                                </td>
                                <td>
                                    <a href="SupplierDetailController?action=Details&txtCompanyName=${supp.companyName}">View</a>
                                </td>
                            </tr>
                            <c:set var="count" value="${count+1}" />
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${message != null}">
                <p style="color: green">${message}</p>
            </c:if>

            <h3>Number of Category: ${(supplierList != null)?supplierList.size():0}</h3>
        </div>
    </body>

</html>