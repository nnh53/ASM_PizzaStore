/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Order;

import Controllers.Product.*;
import Controllers.Supplier.*;
import Controllers.Category.*;
import Controllers.Account.*;
import Constant.DBMessage;
import Constant.ErrorMessage;
import Models.DAO.AccountDAO;
import Models.DAO.CategoryDAO;
import Models.DAO.OrderDAO;
import Models.DAO.ProductDAO;
import Models.DAO.SupplierDAO;
import Models.DAO.UserDAO;
import Models.DTO.Account;
import Models.DTO.AccountError;
import Models.DTO.Category;
import Models.DTO.CategoryError;
import Models.DTO.Order;
import Models.DTO.Product;
import Models.DTO.Supplier;
import Models.DTO.User;
import Models.DTO.UserError;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "OrderUpdateController", urlPatterns = {"/OrderUpdateController"})
public class OrderUpdateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String messageForward = "";
        String url = "OrderDetails.jsp";

        try {
            String orderID = request.getParameter("orderID");
            String customerID = request.getParameter("customerID");
            String orderDate = request.getParameter("orderDate");
            String shipAddress = request.getParameter("shipAddress");
            String status = request.getParameter("status");

            //1. DAO
            OrderDAO dao = new OrderDAO();

            Order toUpdate = new Order(orderID, customerID, orderDate, shipAddress, status);
            dao.updateOrder(toUpdate);
            //2. success
            messageForward = "Update Order successfully";
            request.setAttribute("orderDetails", toUpdate);
        } catch (Exception ex) { //catch ALL exception
            //3.fail
            messageForward = ex.getMessage().toString(); //set message là cái message đã bắt đc
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
