/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Supplier;

import Controllers.Category.*;
import Controllers.Account.*;
import Constant.DBMessage;
import Constant.ErrorMessage;
import Models.DAO.AccountDAO;
import Models.DAO.CategoryDAO;
import Models.DAO.SupplierDAO;
import Models.DAO.UserDAO;
import Models.DTO.Account;
import Models.DTO.AccountError;
import Models.DTO.Category;
import Models.DTO.CategoryError;
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

@WebServlet(name = "SupplierUpdateController", urlPatterns = {"/SupplierUpdateController"})
public class SupplierUpdateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String messageForward = "";
        String url = "SupplierSearch.jsp";

        try {
            String companyName = request.getParameter("txtCompanyName");
            String address = request.getParameter("txtAddress");
            String phone = request.getParameter("txtPhone");
            //1. DAO
            SupplierDAO dao = new SupplierDAO();
            Supplier olddSupp = dao.getSupplierByCompanyName(companyName);
            String id = olddSupp.getSupplierID();
            String status = olddSupp.getStatus();
            Supplier toUpdate = new Supplier(id, companyName, address, phone, status);
            dao.updateSupplier(toUpdate);
            //2. success
            messageForward = "Update Supplier successfully";
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
