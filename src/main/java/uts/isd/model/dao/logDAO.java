package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import uts.isd.model.Logs;

public class logDAO {
    private PreparedStatement readst;
    private String readQuery = "SELECT UserID, ActivityTime, ActivityType from Logs";
    private Connection conn;

    public logDAO(Connection connection) throws SQLException {
        this.conn = connection;
        connection.setAutoCommit(true);
        readst = connection.prepareStatement(readQuery);
    }

    // A new log is inserted into database when User logs in, logs out or registers
    // for a new account
    public void createLog(int UserID, String ActivityTime, String ActivityType) throws SQLException {

        try (PreparedStatement st = conn
                .prepareStatement("Insert into logs(UserID, ActivityTime, ActivityType) Values(?,?,?)")) {
            st.setInt(1, UserID);
            st.setString(2, ActivityTime);
            st.setString(3, ActivityType);
            st.executeUpdate();

        }
    }

    // Logs are retrieved based on the UserID which gets only one user's records
    // This is done through creating arraylist and adding users to the list to be
    // displayed
    public ArrayList<Logs> fetchLogs() throws SQLException {
        ResultSet rs = readst.executeQuery();
        ArrayList<Logs> logs = new ArrayList<Logs>();

        while (rs.next()) {
            String ActivityTime = rs.getString(2);
            String ActivityType = rs.getString(3);

            Logs u = new Logs();

            System.out.println(ActivityTime + " " + ActivityType);

            logs.add(u);
        }
        return logs;
    }

    // Logs are retrieved based on the data of activity of the User within the
    // database
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

    public ArrayList<Logs> fetchSpecificUserLogs(int UserID) throws SQLException {
        String sqlcheck = "Select UserID, ActivityTime, ActivityType from logs where UserID = ?";
        PreparedStatement ps = conn.prepareStatement(sqlcheck);
        ps.setInt(1, UserID);
        ResultSet rs = ps.executeQuery();
        ArrayList<Logs> logs = new ArrayList<Logs>();
        while (rs.next()) {
            int specificUserID = rs.getInt(1);
            String ActivityTime = rs.getString(2);
            String ActvityType = rs.getString(3);
            Logs l = new Logs();
            l.setUserID(UserID);
            l.setActivityTime(ActivityTime);
            l.setActivityType(ActvityType);

            System.out.println(specificUserID + " " + ActivityTime + " " + ActvityType);

            logs.add(l);
        }

        return logs;
    }

    public ArrayList<Logs> fetchSpecificUserLogsByDate(int userID, String date) throws SQLException {
        ArrayList<Logs> logs = new ArrayList<Logs>();
        String sql = "SELECT UserID, ActivityTime, ActivityType FROM logs WHERE UserID = ? AND DATE(ActivityTime) = ?";

        System.out.println("user id" + userID);
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userID);
        ps.setString(2, date);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println("looping");
            Logs log = new Logs();
            log.setUserID(rs.getInt("UserID"));
            log.setActivityTime(rs.getString("ActivityTime"));
            log.setActivityType(rs.getString("ActivityType"));
            logs.add(log);
        }

        return logs;
    }

}
