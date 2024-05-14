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
    private String readQuery = "SELECT * from user";
    private Connection conn;

    public UserDAO(Connection connection) throws SQLException {
        this.conn = connection;
        connection.setAutoCommit(true);
        readst = connection.prepareStatement(readQuery);
    }

    public void adminCreateUser(String firstname, String lastname, String email, int phone, String password,
            String gender, String role) throws SQLException {

        PreparedStatement st = conn.prepareStatement(
                "Insert into user(FirstName, LastName , email, Phone_Number, password ,gender, Role) Values(?,?,?,?,?,?,?)");
        st.setString(1, firstname);
        st.setString(2, lastname);
        st.setString(3, email);
        st.setInt(4, phone);
        st.setString(5, password);
        st.setString(6, gender);
        st.setString(7, role);
        st.executeUpdate();
    }

    public int createUser(String firstname, String lastname, String email, int phone, String password, String gender,
            String role) throws SQLException {
        String sql = "INSERT INTO user (FirstName, LastName, Email, Phone_Number, Password, Gender, Role) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, firstname);
        st.setString(2, lastname);
        st.setString(3, email);
        st.setInt(4, phone);
        st.setString(5, password);
        st.setString(6, gender);
        st.setString(7, role);
        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            throw new SQLException("Creating user failed, no ID obtained.");
        }
    }

    public void adminUpdateUser(User user) throws SQLException {
        PreparedStatement st = conn.prepareStatement(
                "UPDATE user SET FirstName = ?, LastName = ?, Phone_Number = ?, gender = ?, Role = ? WHERE UserID = ?");
        st.setString(1, user.getfirstName());
        st.setString(2, user.getlastname());
        st.setInt(3, user.getPhone());
        st.setString(4, user.getGender());
        st.setString(5, user.getRole());
        st.setInt(6, user.getUserID());

        st.executeUpdate();
    }

    // public ArrayList<User> fetchUsers() throws SQLException {
    // ResultSet rs = readst.executeQuery();
    // ArrayList<User> users = new ArrayList<User>();

    // ResultSet rs = st.getGeneratedKeys();
    // if (rs.next()) {
    // return rs.getInt(1);
    // } else {
    // throw new SQLException("Creating user failed, no ID obtained.");
    // }
    // }

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

    public ArrayList<User> readAllUsers() throws SQLException {
        ResultSet rs = readst.executeQuery();
        ArrayList<User> users = new ArrayList<User>();

        while (rs.next()) {
            int userId = Integer.parseInt(rs.getString(1));
            String firstName = rs.getString(2);
            String lastName = rs.getString(3);
            String email = rs.getString(4);

            int phone = Integer.parseInt(rs.getString(6));
            String role = rs.getString(7);
            String gender = rs.getString(8);

            User u = new User(userId, firstName, lastName, email, phone, gender, role);
            users.add(u);
        }
        return users;
    }

    public User findUser(String email, String password) throws SQLException {
        PreparedStatement st = conn.prepareStatement("Select * from user where email = ? and password =?");
        st.setString(1, email);
        st.setString(2, password);

        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return new User(
                    rs.getInt("UserID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Email"),
                    rs.getInt("Phone_Number"), // Make sure this column exists in your DB
                    rs.getString("Password"),
                    rs.getString("Gender"), // Make sure this column exists in your DB
                    rs.getString("Role"));
        } // get from sql table
        return null;
    }

    public User findUserById(String userId) throws SQLException {
        PreparedStatement st = conn.prepareStatement("Select * from user where UserID = ?");
        st.setString(1, userId);

        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return new User(
                    rs.getInt("UserID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Email"),
                    rs.getInt("Phone_Number"), // Make sure this column exists in your DB
                    rs.getString("Gender"), // Make sure this column exists in your DB
                    rs.getString("Role"));
        } // get from sql table
        return null;
    }

    public ArrayList<User> findUsersByNameNPhone(String fisrtName, String lastName, String phone) throws SQLException {
        PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM user WHERE FirstName LIKE ? AND LastName Like ? AND phone_number LIKE ?");
        st.setString(1, "%" + fisrtName + "%");
        st.setString(2, "%" + lastName + "%");
        st.setString(3, "%" + phone + "%");
        ArrayList<User> users = new ArrayList<User>();

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int userId = Integer.parseInt(rs.getString(1));
            String _firstName = rs.getString(2);
            String _lastName = rs.getString(3);
            String email = rs.getString(4);
            int _phone = Integer.parseInt(rs.getString(6));
            String role = rs.getString(7);
            String gender = rs.getString(8);

            User u = new User(userId, _firstName, _lastName, email, _phone, gender, role);
            users.add(u);
        }

        return users;
    }
}
