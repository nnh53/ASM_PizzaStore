<%-- Document : categoryDetails Created on : Oct 18, 2024, 8:37:50 AM Author : hoangnn --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Models.DTO.User" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
    </head>

    <body>
        <jsp:include page="Header.jsp" />
        <div class="container">
            <form method="post">
                <input type="submit" formaction="LogoutController" value="Logout" /><br />
            </form>

            <!--ton tai account-->
            <c:if test="${orderDetails != null}">
                <h1>Category Details</h1>
                <c:set var="message" value="${requestScope.message}" />
                <c:if test="${message != null}">
                    <text style="color: red">${message}</text>
                </c:if>

                <form action="OrderUpdateController" method="post">
                    <c:set var="error" value="${requestScope.errorDetail}" />

                    orderID <input type="text" readonly name="orderID" value="${orderDetails.orderID}" />

                    customerID <input type="text" name="customerID" value="${orderDetails.customerID}" />
                    <c:if test="${not empty error.productNameError}">
                        <text style="color: red">${error.productNameError}</text>
                    </c:if><br />

                    <div class="row form-group">
                        <div class="col-sm-4">
                            <div class="input-group date" id="datepicker">
                                orderDate<input  name="orderDate" type="text" class="form-control" value="${orderDetails.orderDate}" readonly >
                                <span class="input-group-append">
                                <span class="input-group-text bg-white d-block">
                                    <i class="fa fa-calendar"></i>
                                </span>
                            </span>
                            </div>
                        </div>
                    </div>

                    shipAddress <input type="text" name="shipAddress" value="${orderDetails.shipAddress}" />
                    <c:if test="${not empty error.categoryIDError}">
                        <text style="color: red">${error.categoryIDError}</text>
                    </c:if><br />

                    status <input type="text" name="status" readonly value="${orderDetails.status}" />
                    <c:if test="${not empty error.categoryIDError}">
                        <text style="color: red">${error.categoryIDError}</text>
                    </c:if><br />

                    <input type="submit" value="Update" name="action" />
                </form>

                <a href="OrderView.jsp">Back</a><br />

            </c:if>
        </div>
    </body>

</html>
<script type="text/javascript">
    $(function () {
        $('#datepicker').datepicker();
    });
</script>