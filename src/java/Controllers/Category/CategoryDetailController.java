/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Category;

import Controllers.Account.*;
import Models.DAO.AccountDAO;
import Models.DAO.CategoryDAO;
import Models.DAO.UserDAO;
import Models.DTO.Account;
import Models.DTO.Category;
import Models.DTO.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CategoryDetailController", urlPatterns = {"/CategoryDetailController"})
public class CategoryDetailController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String messageForward = "";
        String url = "";

        try {
            String categoryName = request.getParameter("txtCategoryName");
            //1.DAO
            CategoryDAO categoryDAO = new CategoryDAO();
            Category cate = categoryDAO.getCategoryByCategoryName(categoryName);
            //2.success
            url = "CategoryDetails.jsp";
            request.setAttribute("categoryDetails", cate);
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
