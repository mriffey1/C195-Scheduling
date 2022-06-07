package model;

public class User {
    private int userId;
    private String userName;
    private String password;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;


    public User(int userId, String userName, String password, String createdBy, String lastUpdate, String lastUpdatedBy) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId() {
        this.userId = userId;
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