/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Supplier;

import Models.DAO.SupplierDAO;
import Models.DTO.Supplier;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SupplierDetailController", urlPatterns = {"/SupplierDetailController"})
public class SupplierDetailController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = "SupplierDetails.jsp";
        String messageForward = "";
        try {
            String txtCompanyName = request.getParameter("txtCompanyName");
            //1. DAO
            SupplierDAO dao = new SupplierDAO();

            Supplier supplierDetails = dao.getSupplierByCompanyName(txtCompanyName);
            //2.success
            request.setAttribute("supplierDetails", supplierDetails);
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
