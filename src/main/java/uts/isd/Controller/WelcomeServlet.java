package uts.isd.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.User;

public class WelcomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            request.setAttribute("user", user); // Set user attribute in request scope
            request.getRequestDispatcher("/welcome.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp"); // Redirect to login if no session found
        }
    }

}
