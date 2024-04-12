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
public class RegisterController extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        response.setContentType("text/html");  

        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String favCol = request.getParameter("favcol");
        String gender = request.getParameter("gender");
        String tos = request.getParameter("tos"); 

        User user = new User(email, name, "", password, gender, favCol, tos);

        HttpSession session = request.getSession();
        
        ArrayList<User> users = new ArrayList<>();
        if (session.getAttribute("users") == null) {
            users.add(user);
        }
        else {
            users = (ArrayList<User>) session.getAttribute("users");
            users.add(user);
        }
        session.setAttribute("users", users);

        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("gender", gender);
        request.setAttribute("favcol", favCol);

        RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");  
        rd.forward(request, response);   
    }  
  
    @Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        doPost(req, resp);  
    }  
}  