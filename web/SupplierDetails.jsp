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
        <form method="post">
            <input type="submit" formaction="LogoutController" value="Logout" /><br />
        </form>

        <!--ton tai account-->
        <c:if test="${supplierDetails != null}">
            <h1>Category Details</h1>
            <c:set var="message" value="${requestScope.message}" />
            <c:if test="${message != null}">
                <text style="color: red">${message}</text>
            </c:if>
            <form action="SupplierUpdateController" method="post">
                <c:set var="error" value="${requestScope.errorDetail}" />

                companyName <input type="text" name="txtCompanyName" value="${supplierDetails.companyName}" />
                <c:if test="${not empty error.companyNameError}">
                    <text style="color: red">${error.companyNameError}</text>
                </c:if><br />

                address <input type="text" name="txtAddress" value="${supplierDetails.address}" />
                <c:if test="${not empty error.addressError}">
                    <text style="color: red">${error.addressError}</text>
                </c:if><br />

                phone <input type="text" name="txtPhone" value="${supplierDetails.phone}"/>
                <c:if test="${not empty error.phoneError}">
                    <text style="color: red">${error.phoneError}</text>
                </c:if><br />

                <input type="submit" value="Update" name="action" />
            </form>

            <a href="SupplierView.jsp">Back</a><br />

        </c:if>

    </body>

</html>