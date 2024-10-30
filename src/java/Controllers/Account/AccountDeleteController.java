/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Account;

import Models.DAO.AccountDAO;
import Models.DTO.Account;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AccountDeleteController", urlPatterns = {"/AccountDeleteController"})
public class AccountDeleteController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String searchValue = request.getParameter("txtSearchValue");
        String url = "AccountSearchController" + "?action=Search&txtSearchValue=" + searchValue;
        String messageForward = "";
        String userName = request.getParameter("userName");
        try {
            //1. DAO
            AccountDAO accountDAO = new AccountDAO();

            HttpSession session = request.getSession();
            Account userLoggedIn = (Account) session.getAttribute("accountLoggedIn");

            if (userName.equals(userLoggedIn.getUserName())) {
                messageForward = "This user logged in, can not delete.";
            } else {
                if (accountDAO.deleteAccountByUserName(userName) != null) {
                    messageForward = "The user has been deleted successfully";
                }
            }
        } catch (Exception ex) { //catch ALL exception
            //3. fail
            url = "AccountSearchController" + "?action=Search&txtSearchValue=" + searchValue;
            messageForward = ex.getMessage().toString(); //set message là cái message đã bắt đc
            log(ex.getMessage());
            ex = null; //tránh crash
        } finally {
            request.setAttribute("messageDelete", messageForward);
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
