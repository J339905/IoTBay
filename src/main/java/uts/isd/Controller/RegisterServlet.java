// package uts.isd.Controller;

// import java.io.IOException;
// import java.sql.SQLException;

// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;

// import uts.isd.model.User;
// import uts.isd.model.dao.UserDAO;

// public class RegisterServlet extends HttpServlet {

//     @Override
//     public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//         HttpSession session = request.getSession();

//         String email = request.getParameter("email");
//         String name = request.getParameter("name");
//         String password = request.getParameter("password");
//         String gender = request.getParameter("gender");
//         String favCol = request.getParameter("favcol");
//         int phone = Integer.parseInt(request.getParameter("phone"));

//         UserDAO userDAO = (UserDAO) session.getAttribute("userDAO");
//         public void createUser( String firstname, String lastname, String email,  int phone, String password, String role) throws SQLException{

//         try{
//             userDAO.createUser(phone, name, name, email, phone, password, gender, favCol);//fix this

//             User user = new User();
//             user.setfirstName(name);
//             user.setfirstName(name);
//             user.setPassword(password);
//             user.setPhone(phone);
//             session.setAttribute("user", user);
//             request.getRequestDispatcher("account.jsp").include(request, response);

//         }
//         catch(SQLException e){
//             System.out.print(e);
//         }
//     }
// }
