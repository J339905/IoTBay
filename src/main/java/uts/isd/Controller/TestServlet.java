package uts.isd.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class TestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("Test Servlet is working!");
    }
}

