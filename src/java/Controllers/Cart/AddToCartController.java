/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Cart;

import Models.DAO.ProductDAO;
import Models.DTO.OrderDetail;
import Models.DTO.Product;
import Models.DTO.Cart;
import Models.DTO.CartItem;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCartController"})
public class AddToCartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String messageForward = "";
        String url = "ViewCart.jsp";
        Cart cartList = null;
        try {
            String productName = request.getParameter("txtProductName");
            HttpSession currentSession = request.getSession();
            cartList = (Cart) currentSession.getAttribute("cart");

            ProductDAO productDAO = new ProductDAO();
            Product productToAdd = productDAO.getProductByProductName(productName);

            if (cartList == null) {
                cartList = new Cart();
                currentSession.setAttribute("cart", cartList);
            }

            CartItem rs = cartList.getOrderDetailByProductID(productToAdd.getProductID());
            if (rs != null) {
                rs.setQuantity(rs.getQuantity() + 1);
                messageForward = "Update Cart successfully";
            } else {
                cartList.add(new CartItem("", productToAdd.getProductID(), productToAdd.getProductName(), productToAdd.getProductImageUrl(), 1, productToAdd.getUnitPrice()));
                messageForward = "Add to Cart successfully";
            }
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
