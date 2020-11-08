package AppointmentSystem.Model;

import java.time.ZonedDateTime;
/**
 * Appointments class used to define Appointments.
 * @author josealvarezpulido
 */
public class Appointments {
    /**
     * The ID for the appointment
     */
    private int appointmentId;
    /**
     * The Title for the appointment.
     */
    private String title;
    /**
     * The Description for the appointment.
     */
    private String description;
    /**
     * The location where the appointment takes place.
     */
    private String location;
    /**
     * The type of appointment, defined by its own Class
     */
    private Types type;
    /**
     * The start time of the appointment.
     */
    private ZonedDateTime start;
    /**
     * The end time of the appointment.
     */
    private ZonedDateTime end;
    /**
     * The creation date of the appointment.
     */
    private ZonedDateTime createDate;
    /**
     * The user who created the appointment, is established by the User that is logged in.
     */
    private String createdBy;
    /**
     * The last time the appointment was updated.
     */
    private ZonedDateTime lastUpdate;
    /**
     * The user that last updated the appointment.
     */
    private String lastUpdateBy;
    /**
     * The ID of the customer who has an appointment.
     */
    private int customerId;
    /**
     * The ID of the user that the appointment is assigned to.
     */
    private int userId;
    /**
     * The ID of the contact that the appointment is assigned to.
     */
    private int contactId;
    /**
     * The Name of the Customer.
     */
    private String customerName;
    /**
     * The name of the User.
     */
    private String userName;
    /**
     * The name of the Contact.
     */
    private String contactName;

    /**
     * The constructor that sets the attribute values when creating a new appointment.
     * @param appointmentId sets appointmentId
     * @param title sets title
     * @param description sets description
     * @param location sets location
     * @param type sets type
     * @param start sets start
     * @param end sets end
     * @param createDate sets  createdDate
     * @param createdBy sets createdBy
     * @param lastUpdate sets lastUpdate
     * @param lastUpdateBy sets lastUpdateBy
     * @param customerId sets customerId
     * @param userId sets userId
     * @param contactId sets contactId
     * @param customerName sets customerName
     * @param userName sets userName
     * @param contactName sets contactName
     */
    public Appointments(int appointmentId, String title, String description, String location, Types type, ZonedDateTime start, ZonedDateTime end, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lastUpdateBy, int customerId, int userId, int contactId, String customerName, String userName, String contactName) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.customerName = customerName;
        this.userName = userName;
        this.contactName = contactName;
    }

    /**
     * method for returning the appointmentId.
     * @return appointmentId
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * method for returning the title.
     * @return title
     */
    public String getTitle() {
        return title;
    }
    /**
     * method for setting the title for the appointment.
     * @param title sets title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * method for returning the description.
     * @return description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * method for setting the description for the appointment.
     * @param description sets description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * method for returning the location.
     * @return location
     */
    public String getLocation() {
        return location;
    }
    /**
     * method for setting the location for the appointment.
     * @param location sets location
     */
    public void setLocation(String location) {
        this.location = location;
    }
    /**
     * method for returning the type.
     * @return type
     */
    public Types getType() {
        return type;
    }
    /**
     * method for setting the type for the appointment.
     * @param type sets type
     */
    public void setType(Types type) {
        this.type = type;
    }
    /**
     * method for returning the start.
     * @return start
     */
    public ZonedDateTime getStart() {
        return start;
    }
    /**
     * method for setting the start for the appointment.
     * @param start sets start
     */
    public void setStart(ZonedDateTime start) {
        this.start = start;
    }
    /**
     * method for returning the end.
     * @return end
     */
    public ZonedDateTime getEnd() {
        return end;
    }
    /**
     * method for setting the end for the appointment.
     * @param end sets end
     */
    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }
    /**
     * method for returning the createDate.
     * @return createDate
     */
    public ZonedDateTime getCreateDate() {
        return createDate;
    }
    /**
     * method for setting the CreateDate for the appointment.
     * @param createDate sets createDate
     */
    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }
    /**
     * method for returning the createdBy.
     * @return createBy
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * method for setting the CreatedBy for the appointment.
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
     * method for setting the lastUpdate for the appointment.
     * @param lastUpdate sets lastUpdate
     */
    public void setLastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    /**
     * method for returning the lasUpdateBy.
     * @return lastUpdateBy
     */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }
    /**
     * method for setting the lastUpdateBy for the appointment.
     * @param lastUpdateBy sets lastUpdateBy
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }
    /**
     * method for returning the customerId.
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }
    /**
     * method for setting the customerId for the appointment.
     * @param customerId sets customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    /**
     * method for returning the userId.
     * @return userId
     */
    public int getUserId() {
        return userId;
    }
    /**
     * method for setting the userId for the appointment.
     * @param userId sets userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**
     * method for returning the contactId.
     * @return contactId
     */
    public int getContactId() {
        return contactId;
    }
    /**
     * method for setting the contactId for the appointment.
     * @param contactId sets contactId
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    /**
     * method for returning the userName.
     * @return userName
     */
    public String getUserName() {
        return userName;
    }
    /**
     * method for setting the userName for the appointment.
     * @param userName sets userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * method for returning the customerName.
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }
    /**
     * method for setting the customerName for the appointment.
     * @param customerName sets customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /**
     * method for returning the contactName.
     * @return contactName
     */
    public String getContactName() {
        return contactName;
    }
    /**
     * method for setting the contactName for the appointment.
     * @param contactName sets contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

}
