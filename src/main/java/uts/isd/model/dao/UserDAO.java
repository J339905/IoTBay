package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import uts.isd.model.User;

public class UserDAO {
private PreparedStatement readst;
private String readQuery = "SELECT UserID, FirstName, LastName from User";
private Connection conn;
// private String insertQuery = "SELECT AccountID, FirstName, LastName from Account";


public UserDAO(Connection connection) throws SQLException{
    this.conn = connection;
    connection.setAutoCommit(true);
    readst = connection.prepareStatement(readQuery);
}

public void createUser( int UserID, String firstname, String lastname, String email,  int phone, String password, String role) throws SQLException{
// PreparedStatement st = conn.prepareStatement("Insert into User(UserID, firstname, lastname, email,password, phone_number,role) Values(?,?,?,?,?,?,?,?)");
// st.setInt(1, UserID);
// st.setString(2, firstname);
// st.setString(3, lastname);
// st.setString(4, email);
// st.setInt(5, phone);
// st.setString(6, password);
// st.setString(7, role);
// st.executeUpdate();
Statement st = conn.createStatement();
st.executeUpdate("Insert into user(UserID , FirstName, LastName , email,password, Phone_Number ,`Role`) Values(4,'as','ds','gsd','sad',2343424,'dss')");

}

public ArrayList<User> fetchUsers() throws SQLException{
    ResultSet rs = readst.executeQuery();
    ArrayList<User> users = new ArrayList<User>();

    while(rs.next()){
    String firstName = rs.getString(2);
    String lastName = rs.getString(3);

    User u = new User();

// u.setName(firstName + " " + lastName);


System.out.println(firstName + " " + lastName);

users.add(u);
    }
    return users;
}

}
