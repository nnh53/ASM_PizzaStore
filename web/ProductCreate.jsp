<%-- Document : CreateUser Created on : Oct 18, 2024, 8:36:10 AM Author : hoangnn --%>

<%@page import="Models.DTO.Category"%>
<%@page import="Models.DAO.CategoryDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.DTO.Supplier"%>
<%@page import="Models.DAO.SupplierDAO"%>
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

        <%
            SupplierDAO dao = new SupplierDAO();
            ArrayList<Supplier> supplierList = dao.getAll();
            request.setAttribute("supplierList", supplierList);
            CategoryDAO cateDao = new CategoryDAO();
            ArrayList<Category> categoryList = cateDao.getAll();
            request.setAttribute("categoryList", categoryList);
        %>
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

            supplierID
            <select class="form-select" name="txtSupplierID" >
                <c:forEach var="supp" items="${supplierList}">
                    <option>${supp.supplierID}</option>
                </c:forEach>
            </select>

            categoryID
            <select class="form-select" name="txtCategoryID" >
                <c:forEach var="cate" items="${categoryList}">
                    <option>${cate.categoryID}</option>
                </c:forEach>
            </select>

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