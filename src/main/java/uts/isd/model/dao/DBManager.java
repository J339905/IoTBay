

// package uts.isd.model.dao;

// import uts.isd.model.User;
// import java.sql.*;

// /* 
// * DBManager is the primary DAO class to interact with the database. 
// * Complete the existing methods of this classes to perform CRUD operations with the db.
// */

// public class DBManager {

// private Statement st;
// private Connection conn;
// public DBManager(Connection conn) throws SQLException {       
//    st = conn.createStatement();   
// }

// //Find user by email and password in the database   
// public User findUser(String email, String password) throws SQLException {       
//    //setup the select sql query string       
//    //execute this query using the statement field       
//    //add the results to a ResultSet       
//    //search the ResultSet for a user using the parameters               
//    return null;   
// }

// //Add a user-data into the database   
// // public void addUser(String email, String name, String password, String gender, String favcol) throws SQLException {                   //code for add-operation       
// //   st.executeUpdate("sql query");   

// // }
// // public void addUser(User user) throws SQLException {
// //     String createQuery = "INSERT INTO users(userId, email, name, phone, password, gender, favcol) VALUES (?, ?, ?, ?, ?, ?, ?)";
// //     PreparedStatement pst = conn.prepareStatement(createQuery);
// //     pst.setInt(1, user.getUserID());
// //     pst.setString(2, user.getEmail());
// //     pst.setString(3, user.getName());
// //     pst.setInt(4, user.getPhone());
// //     pst.setString(5, user.getPassword());
// //     pst.setString(6, user.getGender());
// //     pst.setString(7, user.getFavCol());
// //     pst.executeUpdate();
// //     pst.close();
// // }
// public void addUser(User user) throws SQLException {
//         String createQuery = "INSERT INTO users(userId, email, firstname, lastname phone, password, gender, favcol) VALUES (?, ?, ?, ?, ?, ?, ?)";
//         PreparedStatement pst = conn.prepareStatement(createQuery);
//         pst.setInt(1, user.getUserID());
//         pst.setString(2, user.getEmail());
//         pst.setString(3, user.getfirstName());
//         pst.setString(4, user.getlastname());
//         pst.setInt(5, user.getPhone());
//         pst.setString(6, user.getPassword());
//         pst.setString(7, user.getGender());
//         pst.executeUpdate();
//         pst.close();
//     }
    

// //update a user details in the database   
// public void updateUser( String email, String name, String password, String gender, String favcol) throws SQLException {       
//    //code for update-operation   

// }       

// //delete a user from the database   
// public void deleteUser(String email) throws SQLException{       
//    //code for delete-operation   

// }


 

// }