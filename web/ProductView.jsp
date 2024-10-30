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
        <jsp:include page="Header.jsp" />
        <div class="container">

            <!--set variable-->
            <c:set var="accountLoggedIn" value="${accountLoggedIn}" />
            <c:if test="${accountLoggedIn!=null}">
                <c:set var="fullName" value="${accountLoggedIn.fullName}" />
                <c:set var="isStaff" value="${(accountLoggedIn.type =='Staff')? true:false}" />
            </c:if>

            <% ProductDAO dao = new ProductDAO();
                ArrayList<Product> productList = dao.getAll();
                request.setAttribute("productList", productList);
            %>

            <h1>View Product</h1>
            <c:set var="list" value="${productList}" />
            <c:set var="count" value="1" />
            <c:if test="${list!= null}">
                <table border="1" class="table table-hover">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>productName</th>
                            <th>unitPrice</th>
                            <th>productImageUrl</th>
                                <c:if test="${isStaff}">
                                <th>supplierID</th>
                                <th>categoryID</th>
                                <th>status</th>
                                </c:if>
                            <th colspan="2">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="supp" items="${productList}">
                            <tr>
                                <td>${count}</td>
                                <td>${supp.productName}</td>
                                <td>${supp.unitPrice}</td>
                                <td>${supp.productImageUrl}</td>
                                <c:if test="${isStaff}">
                                    <td>${supp.supplierID}</td>
                                    <td>${supp.categoryID}</td>
                                    <td>${supp.status}</td>
                                </c:if>
                                <!--set quyen staff user-->
                                <c:if test="${isStaff}">
                                    <td>
                                        <a
                                            href="ProductDeleteController?action=Delete&txtProductName=${supp.productName}">Delete</a>
                                    </td>
                                    <td>
                                        <a
                                            href="ProductDetailController?action=Details&txtProductName=${supp.productName}">View</a>
                                    </td>
                                    <td>
                                        <a href="AddToCartController?action=Add&txtProductName=${supp.productName}">ADD TO CART</a>
                                    </td>
                                </c:if>
                                <c:if test="${!isStaff}">
                                    <td>
                                        <a href="AddToCartController?action=Add&txtProductName=${supp.productName}">ADD TO CART</a>
                                    </td>
                                </c:if>
                            </tr>
                            <c:set var="count" value="${count+1}" />
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${message != null}">
                <p style="color: green">${message}</p>
            </c:if>

            <h3>Number of Product: ${(productList != null)?productList.size():0}</h3>

            <a href="ViewCart.jsp">View Cart</a>
        </div>
    </body>

</html>