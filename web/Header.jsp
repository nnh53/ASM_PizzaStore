<%-- Document : Header.jsp Created on : Oct 30, 2024, 8:46:39 PM Author: hoangnn --%>

<%@page import="Models.DTO.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.3/dist/minty/bootstrap.min.css" rel="stylesheet">

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
                <!--login ms dc xai chuc nang-->
                <c:set var="isStaff" value="${accountLoggedIn.type == 'Staff'}"/>
                <c:if test="${accountLoggedIn != null}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Product</a>
                        <div class="dropdown-menu">
                            <c:if test="${isStaff}">
                                <a class="dropdown-item" href="ProductCreate.jsp">Create Product</a>
                            </c:if>
                            <a class="dropdown-item" href="ProductSearch.jsp">Search Product</a>
                            <a class="dropdown-item" href="ProductView.jsp">View Product</a>
                        </div>
                    </li>
                    <c:if test="${isStaff}">
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
                    </c:if>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Order</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="OrderCreate.jsp">Create Order</a>
                            <a class="dropdown-item" href="OrderView.jsp">View Order</a>
                        </div>
                    </li>
                    <c:if test="${isStaff}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Account</a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="AccountCreate.jsp">Create Account</a>
                                <a class="dropdown-item" href="AccountSearch.jsp">Search Account</a>
                            </div>
                        </li>
                    </c:if>
                </c:if>
                <li class="nav-item">
                    <a class="nav-link " href="Home.jsp">About</a>
                </li>
            </ul>

            <c:set var="accountLoggedIn" value="${accountLoggedIn}"/>
            <!--not login-->
            <c:if test="${accountLoggedIn == null}">
                <form method="post" action="AccountCreate.jsp" class="ps-1">
                    <button type="submit" class="btn btn-primary btn-sm" style="color: black">Register</button>
                </form>
                <form method="post" action="Login.jsp" class="ps-1">
                    <button type="submit" class="btn btn-primary btn-sm" style="color: black">Login</button>
                </form>
            </c:if>

            <!--neu dc login-->
            <c:if test="${accountLoggedIn != null}">
                <b class="px-2" style="color: black">Welcom, ${accountLoggedIn.userName}</b>
                <a href="ViewCart.jsp">
                    <div style="color: black" class="">
                        <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-cart2" viewBox="0 0 16 16">
                        <path d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5M3.14 5l1.25 5h8.22l1.25-5zM5 13a1 1 0 1 0 0 2 1 1 0 0 0 0-2m-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0m9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2m-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0"/>
                        </svg>
                        Cart
                    </div>
                </a>
                <a href="AccountDetailController?action=Details&userName=${accountLoggedIn.userName}" class="ps-2">
                    <div style="color: black">
                        <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                        <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1"/>
                        </svg>
                    </div>
                </a>
                <form method="post" action="LogoutController" class="ps-1">
                    <button type="submit" class="btn btn-primary btn-sm" style="color: black">Logout</button>
                </form>
            </c:if>
        </div>
    </div>
</nav>