<%-- Document : Home.jsp Created on : Oct 30, 2024, 4:06:43 PM Author: hoangnn --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.3/dist/minty/bootstrap.min.css" rel="stylesheet">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="light">
            <div class="container-fluid">
                <a class="navbar-brand" href="Home.jsp">PizzaStore</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarColor01">
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item">
                            <a class="nav-link active" href="Home.jsp">Home
                                <span class="visually-hidden">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Product</a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="ProductCreate.jsp">Create Product</a>
                                <a class="dropdown-item" href="ProductSearch.jsp">Search Product</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Supplier</a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="SupplierCreate.jsp">Create Supplier</a>
                                <a class="dropdown-item" href="SupplierView.jsp">View Supplier</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Category</a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="CategoryCreate.jsp">Create Category</a>
                                <a class="dropdown-item" href="CategoryView.jsp">View Category</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Features</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Product</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link " href="#">About</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Dropdown</a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="#">Action</a>
                                <a class="dropdown-item" href="#">Another action</a>
                                <a class="dropdown-item" href="#">Something else here</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#">Separated link</a>
                            </div>
                        </li>
                    </ul>
                    <c:set var="accountLoggedIn" value="${accountLoggedIn}"/>
                    <a href="ViewCart.jsp">
                        <div style="color: black" class="px">
                            <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-cart2" viewBox="0 0 16 16">
                            <path d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5M3.14 5l1.25 5h8.22l1.25-5zM5 13a1 1 0 1 0 0 2 1 1 0 0 0 0-2m-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0m9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2m-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0"/>
                            </svg>
                            Cart
                        </div>
                    </a>
                    <a href="AccountDetailController?action=Details&userName=${accountLoggedIn.userName}">
                        <div style="color: black">
                            <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                            <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1"/>
                            </svg>
                        </div>
                    </a>
                </div>
            </div>
        </nav>
        <h1>Home</h1>
        <br>

        <h1>Supplier</h1>
        <a href="SupplierCreate.jsp">SupplierCreate</a>
        <a href="SupplierView.jsp">SupplierView</a>

        <h1>Category</h1>
        <a href="CategoryCreate.jsp">CategoryCreate</a>
        <a href="CategoryView.jsp">CategoryView</a>

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
