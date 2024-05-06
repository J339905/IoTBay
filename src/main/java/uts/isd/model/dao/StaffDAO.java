package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import uts.isd.model.Staff;

public class StaffDAO {
    private PreparedStatement readst;
    private String readQuery = "SELECT StaffID, StaffUserName, StaffPassWord, FirstName, LastName from Staff";
    // private Connection connection;
    // private String insertQuery = "SELECT S, FirstName, LastName from Account";

    public StaffDAO(Connection connection) throws SQLException {
        connection.setAutoCommit(true);
        readst = connection.prepareStatement(readQuery);
    }

    public ArrayList<Staff> fetchStaff() throws SQLException {
        ResultSet rs = readst.executeQuery();
        ArrayList<Staff> staffs = new ArrayList<Staff>();

        while (rs.next()) {
            int StaffID = rs.getInt(1);
            String StaffUserName = rs.getString(2);
            String StaffPassword = rs.getString(3);
            String firstName = rs.getString(4);
            String lastName = rs.getString(5);

            Staff s = new Staff(StaffID, StaffUserName, StaffPassword, firstName, lastName);

            System.out.println(firstName + " " + lastName);

            staffs.add(s);
        }
        return staffs;
    }

}
