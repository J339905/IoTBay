package uts.isd.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.User;

public class LoginController extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        response.setContentType("text/html");  

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        if (session.getAttribute("users") != null) {
            ArrayList<User> users = (ArrayList<User>) session.getAttribute("users");
            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    if (user.getPassword().equals(password)) {
                        request.setAttribute("name", user.getName());
                        request.setAttribute("email", user.getEmail());
                        request.setAttribute("gender", user.getGender());

                        RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");  
                        rd.forward(request, response);  
                    }
                }
            }
        }

        RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
        rd.forward(request, response);  
    }  
  
    @Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        doPost(req, resp);  
    }  
}  