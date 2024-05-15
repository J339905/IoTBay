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

    public void createLog(int UserID, String ActivityTime, String ActivityType) throws SQLException {

        try (PreparedStatement st = conn
                .prepareStatement("Insert into logs(UserID, ActivityTime, ActivityType) Values(?,?,?)")) {
            st.setInt(1, UserID);
            st.setString(2, ActivityTime);
            st.setString(3, ActivityType);
            st.executeUpdate();

        }
    }

    public ArrayList<Logs> fetchLogs() throws SQLException {
        ResultSet rs = readst.executeQuery();
        ArrayList<Logs> logs = new ArrayList<Logs>();

        while (rs.next()) {
            String ActivityTime = rs.getString(2);
            String ActivityType = rs.getString(3);

            Logs u = new Logs();
            u.setUserID(rs.getInt(1));
            u.setActivityTime(ActivityTime);
            u.setActivityType(ActivityType);

            logs.add(u);
        }
        return logs;
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

    public ArrayList<Logs> fetchSpecificUserLogs(int UserID) throws SQLException {
        String sqlcheck = "Select UserID, ActivityTime, ActivityType from logs where UserID = ?";
        PreparedStatement ps = conn.prepareStatement(sqlcheck);
        ps.setInt(1, UserID);
        ResultSet rs = ps.executeQuery();
        ArrayList<Logs> logs = new ArrayList<Logs>();
        while (rs.next()) {
            @SuppressWarnings("unused")
            int specificUserID = rs.getInt(1);
            String ActivityTime = rs.getString(2);
            String ActvityType = rs.getString(3);
            Logs l = new Logs();
            l.setUserID(UserID);
            l.setActivityTime(ActivityTime);
            l.setActivityType(ActvityType);

            logs.add(l);
        }

        return logs;
    }

    public ArrayList<Logs> fetchSpecificUserLogsByDate(int userID, String date) throws SQLException {
        ArrayList<Logs> logs = new ArrayList<Logs>();
        String sql = "SELECT UserID, ActivityTime, ActivityType FROM logs WHERE UserID = ? AND DATE(ActivityTime) = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userID);
        ps.setString(2, date);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Logs log = new Logs();
            log.setUserID(rs.getInt("UserID"));
            log.setActivityTime(rs.getString("ActivityTime"));
            log.setActivityType(rs.getString("ActivityType"));
            logs.add(log);
        }

        return logs;
    }
}
