package uts.isd.controller;

import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import uts.isd.model.User;  
public class RegisterController extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        response.setContentType("text/html");  

        PrintWriter out=response.getWriter();  

        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String favCol = request.getParameter("favcol");
        String gender = request.getParameter("gender");

        User user = new User(email, name, "", password, gender, favCol);
        request.setAttribute("user", user);

        RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");  
        rd.forward(request, response);  
    }  
  
    @Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        doPost(req, resp);  
    }  
}  