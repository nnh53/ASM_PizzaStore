///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Controllers.Account;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@WebServlet(name = "AccountController", urlPatterns = {"/AccountController"})
//public class AccountController extends HttpServlet {
//
//    private final String loginPage = "Login.jsp";
////    private static final String accountCreateController = RouteController.ACCOUNT_CREATE_CONTROLLER.toString();
////    private static final String accountUpdateController = RouteController.ACCOUNT_UPDATE_CONTROLLER.toString();
////    private static final String accountDeleteController = RouteController.ACCOUNT_DELETE_CONTROLLER.toString();
////    private static final String accountDetailsController = RouteController.ACCOUNT_DETAIL_CONTROLLER.toString();
////    private static final String accountSearchController = RouteController.ACCOUNT_SEARCH_CONTROLLER.toString();
//
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        String action = request.getParameter("action");
//        String url = null;
//
//        try {
//            HttpSession session = request.getSession();
//            boolean isLoggedIn = (session.getAttribute("accountLoggedIn") != null);
//
//            if (action.equalsIgnoreCase("Create")) {
//                url = accountCreateController;
//            } else if (isLoggedIn) {
//                if (action.equalsIgnoreCase("Delete")) {
//                    url = accountDeleteController;
//                } else if (action.equalsIgnoreCase("Update")) {
//                    url = accountUpdateController;
//                } else if (action.equalsIgnoreCase("Search")) {
//                    url = accountSearchController;
//                } else if (action.equalsIgnoreCase("Details")) {
//                    url = accountDetailsController;
//                }
//            } else {
//                url = loginPage;
//            }
//        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//}
