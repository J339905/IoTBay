package uts.isd.model;


public class Logs {

    private int UserID;
	private String ActivityTime;
	private String ActivityType;            //properties
    public Logs(){
        
    }
    public Logs(int UserID, String ActivityTime,String ActivityType ){
        this.UserID = UserID;
        this.ActivityTime = ActivityTime;   //constructor
        this.ActivityType = ActivityType;
    }

    public int getUserID() {    //just all getters and setters
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getActivityTime() {
        return ActivityTime;
    }

    public void setActivityTime(String activityTime) {
        ActivityTime = activityTime;
    }

    public String getActivityType() {
        return ActivityType;
    }

    public void setActivityType(String activityType) {
        ActivityType = activityType;
    }
	public String toString(){
        return UserID + " " + ActivityTime + " " + ActivityType;    
    }
}
