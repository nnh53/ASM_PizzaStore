/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Product;

import Controllers.Supplier.*;
import Controllers.Category.*;
import Controllers.Account.*;
import Models.DAO.AccountDAO;
import Models.DAO.CategoryDAO;
import Models.DAO.ProductDAO;
import Models.DAO.SupplierDAO;
import Models.DAO.UserDAO;
import Models.DTO.Account;
import Models.DTO.Category;
import Models.DTO.Product;
import Models.DTO.Supplier;
import Models.DTO.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductDetailController", urlPatterns = {"/ProductDetailController"})
public class ProductDetailController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = "ProductDetails.jsp";
        String messageForward = "";
        try {
            String txtProductName = request.getParameter("txtProductName");
            //1. DAO
            ProductDAO dao = new ProductDAO();

            Product productDetails = dao.getProductByProductName(txtProductName);
            //2.success
            request.setAttribute("productDetails", productDetails);
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
//    url  = RouteController.ACCOUNT_CONTROLLER.toString() + "?action=Search";
