package AppointmentSystem.Model;

import java.time.ZonedDateTime;
/**
 * Users class used to define Users.
 * @author josealvarezpulido
 */
public class Users {
    /**
     * The ID of the User.
     */
    private int userId;
    /**
     * The Name of the User.
     */
    private String userName;
    /**
     * The password of the User.
     */
    private String password;
    /**
     * The created Date of the User.
     */
    private ZonedDateTime createDate;
    /**
     * The User that created the User.
     */
    private String createdBy;
    /**
     * The Date User was updated.
     */
    private ZonedDateTime lastUpdate;
    /**
     * The User that last updated the User.
     */
    private String lastUpdatedBy;

    /**
     * The constructor that sets the attribute values when creating a new User.
     * @param userId sets the userId.
     * @param userName sets the userName.
     * @param password sets the password.
     * @param createDate sets the createDate.
     * @param createdBy sets the createdBy.
     * @param lastUpdate sets the lastUpdate.
     * @param lastUpdatedBy sets the lastUpdatedBy.
     */
    public Users(int userId, String userName, String password, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lastUpdatedBy) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**
     * method for returning the userId.
     * @return userId
     */
    public int getUserId() { return userId; }
    /**
     * method for setting the userId for the Users.
     * @param userId sets userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**
     * method for returning the userName.
     * @return userName
     */
    public String getUserName() {
        return userName;
    }
    /**
     * method for setting the userName for the Users.
     * @param userName sets userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * method for returning the password.
     * @return password
     */
    public String getPassword() {
        return password;
    }
    /**
     * method for setting the password for the Users.
     * @param password sets password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * method for returning the createDate.
     * @return createDate
     */
    public ZonedDateTime getCreateDate() {
        return createDate;
    }
    /**
     * method for setting the createDate for the Users.
     * @param createDate sets createDate
     */
    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }
    /**
     * method for returning the createdBy.
     * @return createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * method for setting the createdBy for the Users.
     * @param createdBy sets createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    /**
     * method for returning the lastUpdate.
     * @return lastUpdate
     */
    public ZonedDateTime getLastUpdate() {
        return lastUpdate;
    }
    /**
     * method for setting the lastUpdate for the Users.
     * @param lastUpdate sets lastUpdate
     */
    public void setLastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    /**
     * method for returning the lastUpdatedBy.
     * @return lastUpdatedBy
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    /**
     * method for setting the lastUpdatedBy for the Users.
     * @param lastUpdatedBy sets lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**
     * This method overrides the toString method used to set an Object to a string.
     * @return userName.
     */
    @Override
    public String toString(){
        return (userName);
    }
}
