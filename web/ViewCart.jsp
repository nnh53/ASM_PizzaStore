<%-- Document : ViewCart.jsp Created on : Oct 30, 2024, 10:38:23 AM Author: hoangnn --%>

<%@page import="Models.DTO.CartItem"%>
<%@page import="Models.DTO.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="Header.jsp" />
        <div class="container">
            <h1>Your Cart</h1>
            <%
                long totalAmount = 0;
                HttpSession currentSession = request.getSession();
                Cart cartList = (Cart) currentSession.getAttribute("cart");
                request.setAttribute("cartList", cartList);
            %>
            <c:set var="cartList" value="${cartList}"/>
            <c:if test="${cartList != null}">
                <table border="1"  class="table table-hover">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>productName</th>
                            <th>unitPrice</th>
                            <th>productImageUrl</th>
                            <th>quantity</th>
                            <th>SubTotal</th>
                            <th colspan="2">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int count = 0;
                            for (CartItem item : cartList) {  // Bắt đầu khối for
                                totalAmount += item.getSubTotal();
                        %>

                    <form action="CartController" method="POST">
                        <input type="hidden" name="txtProductID" value="<%= item.getProductID()%>"/>
                        <tr>
                            <td><%= (++count)%></td>
                            <td><%= item.getProductName()%></td>
                            <td><%= item.getUnitPrice()%></td>
                            <td><%= item.getProductImageUrl()%></td>
                            <td>
                                <input type="number" min="1" name="txtQuantity" value="<%= item.getQuantity()%>"/>
                            </td>
                            <td><%= item.getSubTotal()%></td>
                            <!--user dc xoa hoặc them so luong-->
                            <td>
                                <input type="submit" value="Delete" name="action"/>
                            </td>
                            <td>
                                <input type="submit" value="Update" name="action"/>
                            </td>
                        </tr>
                    </form>
                    <%  // Đóng khối for ở đây
                        }
                    %>
                    </tbody>
                </table>

                <b>Total Amount: <%= totalAmount%> </b>
            </c:if>

            <form action="OrderCreate.jsp">
                <input type="submit" value="Continue"/>
            </form>

            <c:set value="${message}" var="message"/>
            <c:if test="${message != null}">
                <p style="color: green">${message}</p>
            </c:if>
            <a href="ProductView.jsp">Add to cart </a>
        </div>
    </body>
</html>
