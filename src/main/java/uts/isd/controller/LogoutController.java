package uts.isd.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        response.setContentType("text/html");  

        // String name = request.getParameter("name");

        // HttpSession session = request.getSession();

        // if (session.getAttribute("users") != null) {
        //     ArrayList<User> users = (ArrayList<User>) session.getAttribute("users");
        //     for (int i = 0; i < users.size(); i ++) {
        //         if (users.get(i).getName().equals(name)) {
        //             users.remove(i);
        //         }
        //     }
        //     session.setAttribute("users", users);
        // }

        RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
        rd.forward(request, response);  
    }  
  
    @Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        doPost(req, resp);  
    }  
}  
