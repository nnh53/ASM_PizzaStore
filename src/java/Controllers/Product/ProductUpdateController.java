/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Product;

import Controllers.Supplier.*;
import Controllers.Category.*;
import Controllers.Account.*;
import Constant.DBMessage;
import Constant.ErrorMessage;
import Models.DAO.AccountDAO;
import Models.DAO.CategoryDAO;
import Models.DAO.ProductDAO;
import Models.DAO.SupplierDAO;
import Models.DAO.UserDAO;
import Models.DTO.Account;
import Models.DTO.AccountError;
import Models.DTO.Category;
import Models.DTO.CategoryError;
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

@WebServlet(name = "ProductUpdateController", urlPatterns = {"/ProductUpdateController"})
public class ProductUpdateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String messageForward = "";
        String url = "ProductSearch.jsp";

        try {
            String productName = request.getParameter("txtProductName");
            String supplierID = request.getParameter("txtSupplierID");
            String categoryID = request.getParameter("txtCategoryID");
            String unitPrice = request.getParameter("txtUnitPrice");
            String productImageUrl = request.getParameter("txtProductImageUrl");
            //1. DAO
            ProductDAO dao = new ProductDAO();
            Product old = dao.getProductByProductName(productName);
            String id = old.getProductID();
            String status = old.getStatus();
            Product toUpdate = new Product(id, productName, supplierID, categoryID, unitPrice, productImageUrl, status);
            dao.updateProduct(toUpdate);
            //2. success
            messageForward = "Update Product successfully";
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
