/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Category;

import Controllers.Account.*;
import Constant.DBMessage;
import Constant.ErrorMessage;
import Models.DAO.AccountDAO;
import Models.DAO.CategoryDAO;
import Models.DAO.UserDAO;
import Models.DTO.Account;
import Models.DTO.AccountError;
import Models.DTO.Category;
import Models.DTO.CategoryError;
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

@WebServlet(name = "CategoryUpdateController", urlPatterns = {"/CategoryUpdateController"})
public class CategoryUpdateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String messageForward = "";
        String url = "CategorySearch.jsp";

        try {
            //0. validate
            boolean isError = false;
            CategoryError cateError = new CategoryError();
            String categoryName = request.getParameter("txtCategoryName");
            String description = request.getParameter("txtDescription");

            if (categoryName.isEmpty()) {
                cateError.setCategoryNameError("CategoryName cannot be empty");
                isError = true;
            }

            if (description.isEmpty()) {
                cateError.setDescriptionError("Description cannot be empty");
                isError = true;
            }

            //0.5 nếu có lỗi validate quăng ErrorDetail cho jsp
            if (isError) {
                messageForward = ErrorMessage.INPUT_INVALID.toString();
                request.setAttribute("errorDetail", cateError);
                throw new Exception(ErrorMessage.INPUT_INVALID.toString());
            }

            //1. DAO
            CategoryDAO categoryDAO = new CategoryDAO();
            Category olddCate = categoryDAO.getCategoryByCategoryName(categoryName);
            String id = olddCate.getCategoryID();
            String status = olddCate.getStatus();
            Category cateUpdate = new Category(id, categoryName, description, status);
            categoryDAO.updateCategory(cateUpdate);

            //2. success
            messageForward = "Update account successfully";
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
