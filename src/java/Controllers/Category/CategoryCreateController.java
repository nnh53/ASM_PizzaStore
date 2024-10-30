/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Category;

import Constant.DBMessage;
import Models.DAO.CategoryDAO;
import Models.DTO.Category;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CategoryCreateController", urlPatterns = {"/CategoryCreateController"})
public class CategoryCreateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String messageForward = "";
        String url = "CategoryCreate.jsp";
        try {
            //0. validate
//            boolean isError = false;
//            AccountError accountError = new AccountError();
            String categoryName = request.getParameter("txtCategoryName");
            String description = request.getParameter("txtDescription");

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
            //1. DAO
            CategoryDAO categoryDAO = new CategoryDAO();
            String id = categoryDAO.generateID();

            Category categoryToAdd = new Category(id, categoryName, description, DBMessage.ACTIVE.toString());
            categoryDAO.addCategory(categoryToAdd);

            //2. success
            url = "CategoryCreate.jsp";//quay lại
            messageForward = "Create category successfully";

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
