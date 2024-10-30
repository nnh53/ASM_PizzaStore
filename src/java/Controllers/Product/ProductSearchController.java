/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Product;

import Controllers.Account.*;
import Models.DAO.AccountDAO;
import Models.DAO.ProductDAO;
import Models.DTO.Account;
import Models.DTO.Product;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductSearchController", urlPatterns = {"/ProductSearchController"})
public class ProductSearchController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String messageForward = "";
        String url = "ProductSearch.jsp";
        try {
            String searchValue = "";
            searchValue = request.getParameter("txtSearchValue");
            //1. dao
            ProductDAO productDao = new ProductDAO();
            ArrayList<Product> productList = productDao.searchProductByProductIDORProductNameORPrice(searchValue); //2. success
            request.setAttribute("searchResult", productList);
            messageForward = "Search product successfully";
        } catch (Exception ex) { //catch ALL exception
            //3. fail
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
