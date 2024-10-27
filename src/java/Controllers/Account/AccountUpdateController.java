/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Account;

import Constant.DBMessage;
import Constant.ErrorMessage;
import Models.DAO.AccountDAO;
import Models.DAO.UserDAO;
import Models.DTO.Account;
import Models.DTO.AccountError;
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

@WebServlet(name = "AccountUpdateController", urlPatterns = {"/AccountUpdateController"})
public class AccountUpdateController extends HttpServlet {

    private final String userController = "UserController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String messageForward = "";
        String url = "";
        String userName = request.getParameter("txtUserName");
        try {
            //0. validate
            boolean isError = false;
            AccountError accountError = new AccountError();
            String password = request.getParameter("txtPassword");
            String fullname = request.getParameter("txtFullName");
            String staffCheckParam = request.getParameter("chkIsStaff");

            if (!userName.matches(".{3,15}")) {
                accountError.setUserNameError("Username must be 3-15 characters");
                isError = true;
            }

            if (!password.matches(".{3,15}")) {
                accountError.setPasswordError("Password must be 3-15 characters");
                isError = true;
            }

            if (!fullname.matches(".{5,50}")) {
                accountError.setFullNameError("Full name must be 5-50 characters");
                isError = true;
            }

            boolean isStaff = (staffCheckParam == null) ? false : true;

            //0.5 nếu có lỗi validate quăng ErrorDetail cho jsp
            if (isError) {
                messageForward = ErrorMessage.INPUT_INVALID.toString();
                request.setAttribute("errorDetail", accountError);
                throw new Exception(ErrorMessage.INPUT_INVALID.toString());
            }

            //1. DAO
            AccountDAO accountDAO = new AccountDAO();
            Account oldAccount = accountDAO.getAccountByUserName(userName);
            String id = oldAccount.getAccountID();
            if (isStaff) {
                Account userUpdate = new Account(id, userName, password, fullname, "Staff", DBMessage.ACTIVE.toString());
                accountDAO.updateAcccount(userUpdate);
            } else {
                Account userToUpdate = new Account(id, userName, password, fullname, "User", DBMessage.ACTIVE.toString());
                accountDAO.updateAcccount(userToUpdate);
            }
            //2. success
            url = "AccountDetailController" + "?action=Details&&UserName=" + userName;
            messageForward = "Update account successfully";
        } catch (Exception ex) { //catch ALL exception
            //3. fail
            url = "AccountDetailController" + "?action=Details&&UserName=" + userName;
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
