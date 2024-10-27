package Controllers.Authentication;

import Constant.ErrorMessage;
import Models.DAO.AccountDAO;
import Models.DAO.UserDAO;
import Models.DTO.Account;
import Models.DTO.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String messageForward = "";
        String url = "404.jsp";

        try {
            String userName = request.getParameter("txtAccountName");
            String password = request.getParameter("txtPassword");
            //1.dao
            AccountDAO accountDAO = new AccountDAO();
            Account account = accountDAO.login(userName, password);
            //2.success
            HttpSession session = request.getSession();
            session.setAttribute("accountLoggedIn", account);
            if (account.getType().matches("Staff")) {
//                url = "AccountSearch.jsp";
                url = "CategoryCreate.jsp";
            } else {
                url = "AccountDetailController" + "?action=Details&&userName=" + userName;
            }
        } catch (Exception ex) { //catch ALL exception
            //3.fail
            url = "Login.jsp";
            messageForward = ex.getMessage().toString(); //dao quăng ex có message
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
