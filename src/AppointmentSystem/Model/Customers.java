package AppointmentSystem.Model;

import java.time.ZonedDateTime;
/**
 * Customers class used to define Customers
 * @author josealvarezpulido
 */
public class Customers {
    /**
     * The ID of the customer.
     */
    private int customerId;
    /**
     * The name of the customer.
     */
    private String customerName;
    /**
     * The address of the customer.
     */
    private String address;
    /**
     * The postalCode of the customer.
     */
    private String postalCode;
    /**
     * The phone number of the customer.
     */
    private String phone;
    /**
     * The created Date of the customer
     */
    private ZonedDateTime createDate;
    /**
     * The User that created the Customer.
     */
    private String createdBy;
    /**
     * The updated Date of the customer.
     */
    private ZonedDateTime lastUpdate;
    /**
     * The  User that last updated the customer.
     */
    private String lastUpdatedBy;
    /**
     * The division name for where the customer resides.
     */
    private String divisionName;
    /**
     * The division ID for where the customer resides.
     */
    private int divisionId;

    /**
     * The constructor that sets the attribute values when creating a new Customer.
     * @param customerId sets customerId.
     * @param customerName sets customerName.
     * @param address sets address.
     * @param postalCode sets postalCode.
     * @param phone sets phone.
     * @param createDate sets createDate.
     * @param createdBy sets createdBy.
     * @param lastUpdate sets lastUpdate.
     * @param lastUpdatedBy sets lasUpdatedBy.
     * @param divisionId sets divisionId.
     * @param divisionName sets divisionName.
     */
    public Customers(int customerId, String customerName, String address, String postalCode, String phone, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lastUpdatedBy, int divisionId, String divisionName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;
        this.divisionName = divisionName;
    }

    /**
     * method for returning the customerId.
     * @return customerId.
     */
    public int getCustomerId() {
        return customerId;
    }
    /**
     * method for setting the customerId for the Customer.
     * @param customerId sets customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    /**
     * method for returning the customerName.
     * @return customerName.
     */
    public String getCustomerName() {
        return customerName;
    }
    /**
     * method for setting the customerName for the Customer.
     * @param customerName sets customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /**
     * method for returning the address.
     * @return address.
     */
    public String getAddress() {
        return address;
    }
    /**
     * method for setting the address for the Customer.
     * @param address sets address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * method for returning the postalCode.
     * @return postalCode.
     */
    public String getPostalCode() {
        return postalCode;
    }
    /**
     * method for setting the postalCode for the Customer.
     * @param postalCode sets postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    /**
     * method for returning the phone.
     * @return phone.
     */
    public String getPhone() {
        return phone;
    }
    /**
     * method for setting the phone for the Customer.
     * @param phone sets phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * method for returning the createDate.
     * @return createDate.
     */
    public ZonedDateTime getCreateDate() {
        return createDate;
    }
    /**
     * method for setting the createDate for the Customer.
     * @param createDate sets createDate
     */
    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }
    /**
     * method for returning the createdBy.
     * @return createdBy.
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * method for setting the createdBy for the Customer.
     * @param createdBy sets createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    /**
     * method for returning the lastUpdate.
     * @return lastUpdate.
     */
    public ZonedDateTime getLastUpdate() {
        return lastUpdate;
    }
    /**
     * method for setting the lastUpdate for the Customer.
     * @param lastUpdate sets lasUpdate
     */
    public void setLastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    /**
     * method for returning the lastUpdateBy.
     * @return lastUpdateBy.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    /**
     * method for setting the lastUpdateBy for the Customer.
     * @param lastUpdatedBy sets lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**
     * method for returning the divisionId.
     * @return divisionId.
     */
    public int getDivisionId() {
        return divisionId;
    }
    /**
     * method for setting the divisionId for the Customer.
     * @param divisionId sets divisionId
     */
    public void setDivisionId(int divisionId) { this.divisionId = divisionId; }
    /**
     * method for returning the divisionName.
     * @return divisionName.
     */
    public String getDivisionName() {
        return divisionName;
    }
    /**
     * method for setting the divisionName for the Customer.
     * @param divisionName sets divisionName
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
    /**
     * This method overrides the toString method used to set an Object to a string.
     * @return customerName.
     */
    @Override
    public String toString(){
        return (customerName);
    }
}

