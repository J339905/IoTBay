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
    // private String insertQuery = "SELECT AccountID, FirstName, LastName from
    // Account";

    public UserDAO(Connection connection) throws SQLException {
        this.conn = connection;
        connection.setAutoCommit(true);
        readst = connection.prepareStatement(readQuery);
    }

    // public void createUser(String firstname, String lastname, String email, int phone, String password, String gender, String role)
    //         throws SQLException {
    //     // PreparedStatement st = conn.prepareStatement("Insert into User(UserID,
    //     // firstname, lastname, email,password, phone_number,role)
    //     // Values(?,?,?,?,?,?,?,?)");
    //     // st.setInt(1, UserID);
    //     // st.setString(2, firstname);
    //     // st.setString(3, lastname);
    //     // st.setString(4, email);
    //     // st.setInt(5, phone);
    //     // st.setString(6, password);
    //     // st.setString(7, role);
    //     // st.executeUpdate();
        
    //     Statement st = conn.createStatement();
    //     st.executeUpdate("Insert into user(FirstName, LastName , email,password, Phone_Number ,Role, gender) Values('as','ds','gsd','sad',2343424,'dss', 'Male')");

<<<<<<< HEAD
    // }

    public void createUser(String firstname, String lastname, String email, int phone, String password, String gender, String role) throws SQLException {
		PreparedStatement st = conn.prepareStatement("Insert into user(FirstName, LastName , email, Phone_Number, password ,gender, Role) Values(?,?,?,?,?,?,?)");
		st.setString(1, firstname);
        st.setString(2, lastname);
		st.setString(3, email);
		st.setInt(4, phone);
		st.setString(5, password);
        st.setString(6, gender);
        st.setString(7, role);
		st.executeUpdate();
	}
    public ArrayList<User> fetchUsers() throws SQLException {
        ResultSet rs = readst.executeQuery();
        ArrayList<User> users = new ArrayList<User>();
=======
        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1); 
        } else {
            throw new SQLException("Creating user failed, no ID obtained.");
        }
    }

    public User updateUser(String firstname, String lastname, int phone, String password, String gender, String role,
            String email) throws SQLException {
        String sqlUpdate = "UPDATE user SET FirstName = ?, LastName = ?, Phone_Number = ?, Password = ?, Gender = ?, Role = ? WHERE Email = ?";
        try (PreparedStatement st = conn.prepareStatement(sqlUpdate)) {
            st.setString(1, firstname);
            st.setString(2, lastname);
            st.setInt(3, phone);
            st.setString(4, password);
            st.setString(5, gender);
            st.setString(6, role);
            st.setString(7, email);

            int affectedRows = st.executeUpdate();

            if (affectedRows > 0) {
                String sqlSelect = "SELECT * FROM user WHERE Email = ?";
                try (PreparedStatement selectStmt = conn.prepareStatement(sqlSelect)) {
                    selectStmt.setString(1, email);
                    try (ResultSet rs = selectStmt.executeQuery()) {
                        if (rs.next()) {
                            // Return the updated user
                            return new User(
                                    rs.getInt("UserID"),
                                    rs.getString("FirstName"),
                                    rs.getString("LastName"),
                                    rs.getString("Email"),
                                    rs.getInt("Phone_Number"),
                                    rs.getString("Password"),
                                    rs.getString("Gender"),
                                    rs.getString("Role"));
                        }

                    }
                }
            }
        }
        return null; 
    }
    public void deleteUser(int userID) throws SQLException {
        try {
            conn.setAutoCommit(false);

            try (PreparedStatement st1 = conn.prepareStatement("DELETE FROM logs WHERE UserID = ?")) {
                st1.setInt(1, userID);
                st1.executeUpdate();
            }

            try (PreparedStatement st2 = conn.prepareStatement("DELETE FROM user WHERE UserID = ?")) {
                st2.setInt(1, userID);
                st2.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
>>>>>>> NasBranch-LogAccess

        while (rs.next()) {
            String firstName = rs.getString(2);
            String lastName = rs.getString(3);

            User u = new User();

            // u.setName(firstName + " " + lastName);

            System.out.println(firstName + " " + lastName);

            users.add(u);
        }
        return users;
    }
    public User findExistingUser(String email) throws SQLException {
        String sql = "SELECT * FROM user WHERE email = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, email);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int userID = retrieveUserId(email, "", false);
                    return new User(
                            userID,
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getString("Email"),
                            rs.getInt("Phone_Number"),
                            rs.getString("Password"),
                            rs.getString("Gender"),
                            rs.getString("Role"));
                }
            }
        }
        return null;
    }

<<<<<<< HEAD
=======
    public int retrieveUserId(String email, String password, boolean usePassword) throws SQLException {
        String sql = "SELECT UserID FROM user WHERE Email = ?";
        if (usePassword == true) {
            sql = sql + " AND password = ?";
        }

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, email);
            if (usePassword) {
                st.setString(2, password);
            }

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("UserID");
                }
            }
        }
        return 0; 
    }

    public boolean doesUserExist(int userID) throws SQLException {
        String sql = "SELECT COUNT(1) FROM user WHERE UserID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    

>>>>>>> NasBranch-LogAccess
    public ArrayList<User> readAllUsers() throws SQLException {
        ResultSet rs = readst.executeQuery();
        ArrayList<User> users = new ArrayList<User>();

        while (rs.next()) {
            int userId = Integer.parseInt(rs.getString(1));
            String firstName = rs.getString(2);
            String lastName = rs.getString(3);
            String email = rs.getString(4);

            int phone = Integer.parseInt(rs.getString(5));
            String password = rs.getString(6);
            String role = rs.getString(7);
            String gender = rs.getString(8);

            User u = new User(userId, firstName, lastName, email, phone, password, gender, role);
            users.add(u);
        }
        return users;
    }

}