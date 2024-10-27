/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Account;

import Models.DAO.AccountDAO;
import Models.DAO.UserDAO;
import Models.DTO.Account;
import Models.DTO.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AccountSearchController", urlPatterns = {"/AccountSearchController"})
public class AccountSearchController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String messageForward = "";
        String url = "";
        try {
            String searchValue = "";
            searchValue = request.getParameter("txtSearchValue");
            //1. dao
            AccountDAO accountDAO = new AccountDAO();
            ArrayList<Account> accountList = accountDAO.searchAccountFullName(searchValue);
            //2. success
            request.setAttribute("searchResult", accountList);
            url = "AccountSearch.jsp";
            messageForward = "Search account successfully";
        } catch (Exception ex) { //catch ALL exception
            //3. fail
            url = "AccountSearch.jsp";
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
