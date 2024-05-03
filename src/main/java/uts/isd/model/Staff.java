package uts.isd.model;
import java.io.Serializable;


public class Staff {
    private int staffID;
    private String StaffUsername;
    private String StaffPassword;
    private String FirstName;
    private String LastName;

    public Staff(int staffID, String StaffUsername, String StaffPassword, String FirstName, String LastName) {
        this.staffID =staffID;
        this.StaffUsername = StaffUsername;
        this.StaffPassword = StaffPassword;
        this.FirstName = FirstName;
        this.LastName = LastName;
    }
    public void setstaffID(int staffID) {
        this.staffID = staffID;
    }
    public void setAdminUsername(String adminUsername) {
        this.StaffUsername = adminUsername;
    }

    public void setAdminPassword(String adminPassword) {
        this.StaffPassword = adminPassword;
    }
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }
    public int getStaffID() {
        return staffID;
    }
    public String getStaffUsername() {
        return StaffUsername;
    }

    public String getStaffPassword() {
        return StaffPassword;
    }
    public String getFirstName() {
        return FirstName;
    }
    public String getLastName() {
        return LastName;
    }
}
