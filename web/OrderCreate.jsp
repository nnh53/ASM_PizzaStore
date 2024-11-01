<%-- Document : CreateUser Created on : Oct 18, 2024, 8:36:10 AM Author : hoangnn --%>

<%@page import="java.util.ArrayList"%>
<%@page import="Models.DTO.Account"%>
<%@page import="Models.DAO.AccountDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

            <!--set variable-->
            <c:set var="accountLoggedIn" value="${accountLoggedIn}" />
            <c:if test="${accountLoggedIn!=null}">
                <c:set var="fullName" value="${accountLoggedIn.fullName}" />
                <c:set var="isStaff" value="${(accountLoggedIn.type =='Staff')? true:false}" />
            </c:if>

            <%
                AccountDAO dao = new AccountDAO();
                ArrayList<Account> accountList = dao.getAll();
                request.setAttribute("accountList", accountList);
            %>

            <h1>Create Order</h1>
            <c:set var="message" value="${requestScope.message}" />
            <c:if test="${message != null}">
                ${message}
            </c:if>

            <form action="OrderCreateController" method="post">
                <input type="hidden" value="${cart}" name="cart"/>
                <c:set var="error" value="${requestScope.errorDetail}" />

                <c:if test="${isStaff}">
                    customerID
                    <select class="form-select" name="customerID" >
                        <c:forEach var="acc" items="${accountList}">
                            <option>${acc.accountID}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <c:if test="${!isStaff}">
                    customerID
                    <select class="form-select" name="customerID"  >
                        <option>${accountLoggedIn.accountID}</option>
                    </select>
                </c:if>


                <div class="row form-group">
                    <div class="col-sm-4">
                        <div class="input-group date" id="datepicker">
                            orderDate<input  name="orderDate" type="text" class="form-control" readonly>
                            <span class="input-group-append">
                                <span class="input-group-text bg-white d-block">
                                    <i class="fa fa-calendar"></i>
                                </span>
                            </span>
                        </div>
                    </div>
                </div>

                shipAddress <input type="text" name="shipAddress" />
                <c:if test="${not empty error.categoryIDError}">
                    <text style="color: red">${error.categoryIDError}</text>
                </c:if><br />

                <input type="submit" value="Create" name="action" /><br />

                <a href="Home.jsp">Back</a><br />
            </form>
        </div>
    </body>
</html>
<script type="text/javascript">
    $(function () {
        $('#datepicker').datepicker();
    });
</script>