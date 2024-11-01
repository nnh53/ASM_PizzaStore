/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Order;

import Constant.DBMessage;
import Models.DAO.OrderDAO;
import Models.DTO.OrderDetail;
import Models.DAO.OrderDetailDAO;
import Models.DTO.Order;
import Models.DTO.Cart;
import Models.DTO.CartItem;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "OrderCreateController", urlPatterns = {"/OrderCreateController"})
public class OrderCreateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String messageForward = "";
        String url = "OrderCreate.jsp";
        try {
            Cart cart = (Cart) request.getAttribute("cart");
            request.setAttribute("cart", cart);
            //0. validate
//            boolean isError = false;
//            AccountError accountError = new AccountError();
            String customerID = request.getParameter("customerID");
            String orderDate = request.getParameter("orderDate");
            String shipAddress = request.getParameter("shipAddress");
//            if (!userName.matches(".{3,15}")) {
//                accountError.setUserNameError("Username must be 3-15 characters");
//                isError = true;
//            }
//
//            if (!password.matches(".{3,15}")) {
//                accountError.setPasswordError("Password must be 3-15 characters");
//                isError = true;
//            }
//
//            if (!fullname.matches(".{5,50}")) {
//                accountError.setFullNameError("Full name must be 5-50 characters");
//                isError = true;
//            }
//
//            boolean isStaff = (staffCheckParam == null) ? false : true;
//
//            //0.5 nếu có lỗi validate quăng ErrorDetail cho jsp
//            if (isError) {
//                messageForward = ErrorMessage.INPUT_INVALID.toString();
//                request.setAttribute("errorDetail", accountError);
//                url = "AccountCreate.jsp";//quay lại
//                throw new Exception(ErrorMessage.INPUT_INVALID.toString());
//            }

            HttpSession currentSession = request.getSession();
            Cart cartList = (Cart) currentSession.getAttribute("cart");
            if (cartList == null || cartList.size() == 0) {

                url = "ViewCart.jsp";
                throw new Exception("Cart is empty cannot create Order");
            }

            //1. DAO
            OrderDAO dao = new OrderDAO();
            String id = dao.generateID();

            Order orderToAdd = new Order(id, customerID, orderDate, shipAddress, DBMessage.ACTIVE.toString());
            dao.addOrder(orderToAdd);

            OrderDetailDAO orderDetailDao = new OrderDetailDAO();

            //2.dao xuống cho order detail
            for (CartItem cartItem : cartList) {
                OrderDetail orderDetailToAdd = new OrderDetail(orderToAdd.getOrderID(), cartItem.getProductID(), cartItem.getQuantity(), cartItem.getUnitPrice());
                //dao
                orderDetailDao.addOrderDetail(orderDetailToAdd);
            }

            //2. success
            messageForward = "Create Order successfully";

        } catch (Exception ex) { //catch ALL exception
            //3. fail
            messageForward = ex.getMessage(); //set message là cái message đã bắt đc
            log(ex.getMessage());
            ex = null; //tránh crash
        } finally {
            request.setAttribute("message", messageForward);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
