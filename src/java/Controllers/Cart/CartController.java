/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Cart;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CartController", urlPatterns = {"/CartController"})
public class CartController extends HttpServlet {

    private final String addCartController = "AddToCartController";
    private final String viewCartController = "ViewCartController";
    private final String deleteCartController = "DeleteCartController";
    private final String updateCartController = "UpdateCartController";
    private final String saveCartController = "SaveCartController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = viewCartController, action;
        try {
            action = request.getParameter("action");
            if (action.equals("Add")) {
                url = addCartController;
            } else if (action.equals("View Cart")) {
                url = viewCartController;
            } else if (action.equals("Delete")) {
                url = deleteCartController;
            } else if (action.equals("Update")) {
                url = updateCartController;
            } else if (action.equals("Save")) {
                url = saveCartController;
            }
        } catch (Exception ex) {
            log("CartController has error: " + ex.getMessage());
        } finally {
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
