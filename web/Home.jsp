<%-- Document : Home.jsp Created on : Oct 30, 2024, 4:06:43 PM Author: hoangnn --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>--%>
<%--<link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.3/dist/minty/bootstrap.min.css" rel="stylesheet">--%>
<!DOCTYPE html>
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

            <h1>Home</h1>
            <br>
            <c:if test="${isStaff}">
                <h1>Supplier</h1>
                <a href="SupplierCreate.jsp">SupplierCreate</a>
                <a href="SupplierView.jsp">SupplierView</a>

                <h1>Category</h1>
                <a href="CategoryCreate.jsp">CategoryCreate</a>
                <a href="CategoryView.jsp">CategoryView</a>


                <h1>Account</h1>
                <a href="AccountCreate.jsp">AccountCreate</a>
                <a href="AccountSearch.jsp">AccountSearch</a>
                <a href="AccountDetails.jsp">Account Details</a>
            </c:if>

            <h1>Order</h1>
            <a href="OrderCreate.jsp">OrderCreate</a>
            <a href="OrderView.jsp">OrderView</a>

            <h1>Product</h1>
            <a href="ProductCreate.jsp">ProductCreate</a>
            <a href="ProductSearch.jsp">ProductSearch</a>
            <a href="ProductView.jsp">View Product</a>

            <h1>Cart</h1>
            <a href="ViewCart.jsp">ViewCart</a>

        </div>
    </body>
</html>
