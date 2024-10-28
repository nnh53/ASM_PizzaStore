<%-- Document : Search Created on : Oct 18, 2024, 8:37:25 AM Author : hoangnn --%>
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
        <c:set var="accountLoggedIn" value="${accountLoggedIn}" />
        <!--welcome-->
        <c:if test="${userLoggedIn!=null}">
            <c:set var="lastName" value="${accountLoggedIn.lastName}" />
        </c:if>
        <h3>Welcome <text style="color: red">${lastName}</text></h3>

        <% ProductDAO dao = new ProductDAO();
            ArrayList<Product> productList = dao.getAll();
            request.setAttribute("productList", productList);
        %>

        <c:set var="list" value="${productList}" />
        <c:set var="count" value="1" />
        <c:if test="${list!= null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>productName</th>
                        <th>supplierID</th>
                        <th>categoryID</th>
                        <th>unitPrice</th>
                        <th>productImageUrl</th>
                        <th>status</th>
                        <th colspan="2">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="supp" items="${productList}">
                        <tr>
                            <td>${count}</td>
                            <td>${supp.productName}</td>
                            <td>${supp.supplierID}</td>
                            <td>${supp.categoryID}</td>
                            <td>${supp.unitPrice}</td>
                            <td>${supp.productImageUrl}</td>
                            <td>${supp.status}</td>
                            <td>
                                <a
                                    href="ProductDeleteController?action=Delete&txtProductName=${supp.productName}">Delete</a>
                            </td>
                            <td>
                                <a
                                    href="ProductDetailController?action=Details&txtProductName=${supp.productName}">View</a>
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

        <h3>Number of Category: ${(productList != null)?productList.size():0}</h3>
    </body>

</html>