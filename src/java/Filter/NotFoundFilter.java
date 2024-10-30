/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filter;

import Models.DTO.Account;
import Models.DTO.User;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hoangnn
 */
public class NotFoundFilter implements Filter {

    private List<String> allowedURLs = Arrays.asList(
            "/ASM_PizzaStore/Login.jsp",
            "/ASM_PizzaStore/",
            "/ASM_PizzaStore/NotFound.jsp",
            "NotFound.jsp"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        Account userLoggedIn = (session != null) ? (Account) session.getAttribute("accountLoggedIn") : null;

        String requestURI = httpRequest.getRequestURI();

        if (allowedURLs.contains(requestURI)) {
            RequestDispatcher rd = request.getRequestDispatcher(requestURI);
            rd.forward(request, response);
        } else {
            httpResponse.sendRedirect("NotFound.jsp");
        }
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
    }

}
