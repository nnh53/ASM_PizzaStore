<%-- Document : CreateUser Created on : Oct 18, 2024, 8:36:10 AM Author : hoangnn --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="Header.jsp" />
        <h1>Create Product</h1>
        <c:set var="message" value="${requestScope.message}" />
        <c:if test="${message != null}">
            ${message}
        </c:if>

        <form action="ProductCreateController" method="post">
            <c:set var="error" value="${requestScope.errorDetail}" />

            productName <input type="text" name="txtProductName" value="" />
            <c:if test="${not empty error.productNameError}">
                <text style="color: red">${error.productNameError}</text>
            </c:if><br />

            supplierID <input type="text" name="txtSupplierID" />
            <c:if test="${not empty error.supplierIDError}">
                <text style="color: red">${error.supplierIDError}</text>
            </c:if><br />

            categoryID <input type="text" name="txtCategoryID" />
            <c:if test="${not empty error.categoryIDError}">
                <text style="color: red">${error.categoryIDError}</text>
            </c:if><br />

            unitPrice <input type="text" name="txtUnitPrice" />
            <c:if test="${not empty error.unitPriceError}">
                <text style="color: red">${error.unitPriceError}</text>
            </c:if><br />

            productImageUrl <input type="text" name="txtProductImageUrl" />
            <c:if test="${not empty error.productImageUrlError}">
                <text style="color: red">${error.productImageUrlError}</text>
            </c:if><br />

            <input type="submit" value="Create" name="action" /><br />

            <a href="Home.jsp">Back</a><br />
        </form>
    </body>

</html>