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
        <div class="container">

            <h1>Create Supplier</h1>
            <c:set var="message" value="${requestScope.message}" />
            <c:if test="${message != null}">
                ${message}
            </c:if>

            <form action="SupplierCreateController" method="post">
                <c:set var="error" value="${requestScope.errorDetail}" />

                companyName <input type="text" name="txtCompanyName" value="" />
                <c:if test="${not empty error.companyNameError}">
                    <text style="color: red">${error.companyNameError}</text>
                </c:if><br />

                address <input type="text" name="txtAddress" />
                <c:if test="${not empty error.addressError}">
                    <text style="color: red">${error.addressError}</text>
                </c:if><br />

                phone <input type="text" name="txtPhone" />
                <c:if test="${not empty error.phoneError}">
                    <text style="color: red">${error.phoneError}</text>
                </c:if><br />

                <input type="submit" value="Create" name="action" /><br />

                <a href="Home.jsp">Back</a><br />
            </form>
        </div>
    </body>

</html>