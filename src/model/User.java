package model;

public class User {
    private int userID;
    private String userName;
    private String password;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;


    public User(int userID, String userName, String password, String createdBy, String lastUpdate, String lastUpdatedBy) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID() {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName() {
        this.userName = userName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy() {
        this.createdBy = createdBy;
    }
}