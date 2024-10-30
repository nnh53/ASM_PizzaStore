<%-- Document : Home.jsp Created on : Oct 30, 2024, 4:06:43 PM Author: hoangnn --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.3/dist/minty/bootstrap.min.css" rel="stylesheet">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Home</h1>
        <br>

        <h1>Supplier</h1>
        <a href="SupplierCreate.jsp">SupplierCreate</a>
        <a href="SupplierView.jsp">SupplierView</a>

        <h1>Category</h1>
        <a href="CategoryCreate.jsp">CategoryCreate</a>
        <a href="CategorySearch.jsp">CategorySearch</a>

        <h1>Product</h1>
        <a href="ProductCreate.jsp">ProductCreate</a>
        <a href="ProductSearch.jsp">ProductSearch</a>


        <h1>Account</h1>
        <a href="AccountCreate.jsp">AccountCreate</a>
        <a href="AccountSearch.jsp">AccountSearch</a>

        <h1>Order</h1>
        <a href="OrderCreate.jsp">OrderCreate</a>
        <a href="OrderView.jsp">OrderView</a>


        <h1>Cart</h1>
        <a href="ViewCart.jsp">ViewCart</a>
        <a href="ProductSearch.jsp">ProductSearch</a>
    </body>
</html>
