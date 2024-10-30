<%-- Document : categoryDetails Created on : Oct 18, 2024, 8:37:50 AM Author : hoangnn --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Models.DTO.User" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body>
        <jsp:include page="Header.jsp" />
        <div class="container">

            <!--ton tai account-->
            <c:if test="${productDetails != null}">
                <h1>Category Details</h1>
                <c:set var="message" value="${requestScope.message}" />
                <c:if test="${message != null}">
                    <text style="color: red">${message}</text>
                </c:if>
                <form action="ProductUpdateController" method="post">
                    <c:set var="error" value="${requestScope.errorDetail}" />

                    productName <input type="text" name="txtProductName" value="${productDetails.productName}" />
                    <c:if test="${not empty error.productNameError}">
                        <text style="color: red">${error.productNameError}</text>
                    </c:if><br />

                    supplierID <input type="text" name="txtSupplierID" value="${productDetails.supplierID}"/>
                    <c:if test="${not empty error.supplierIDError}">
                        <text style="color: red">${error.supplierIDError}</text>
                    </c:if><br />

                    categoryID <input type="text" name="txtCategoryID" value="${productDetails.categoryID}" />
                    <c:if test="${not empty error.categoryIDError}">
                        <text style="color: red">${error.categoryIDError}</text>
                    </c:if><br />

                    unitPrice <input type="text" name="txtUnitPrice" value="${productDetails.unitPrice}" />
                    <c:if test="${not empty error.unitPriceError}">
                        <text style="color: red">${error.unitPriceError}</text>
                    </c:if><br />

                    productImageUrl <input type="text" name="txtProductImageUrl" value="${productDetails.productImageUrl}"/>
                    <c:if test="${not empty error.productImageUrlError}">
                        <text style="color: red">${error.productImageUrlError}</text>
                    </c:if><br />

                    <input type="submit" value="Update" name="action" />
                </form>

                <a href="SupplierView.jsp">Back</a><br />

            </c:if>

        </div>
    </body>

</html>