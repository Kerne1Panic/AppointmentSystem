package AppointmentSystem.Model;

import java.time.ZonedDateTime;
/**
 * Divisions class used to define Divisions.
 * @author josealvarezpulido
 */
public class Divisions {
    /**
     * The ID of the Division.
     */
    private int divisionId;
    /**
     * The Name of the Division
     */
    private String DivisionName;
    /**
     * The Created Date of the Division.
     */
    private ZonedDateTime createDate;
    /**
     * The User that created the Division.
     */
    private String createdBy;
    /**
     * The Date the Division was last Updated.
     */
    private ZonedDateTime lastUpdate;
    /**
     * The User that last updated the Division.
     */
    private String lastUpdatedBy;
    /**
     * The ID of the Country the Division is in.
     */
    private int countryId;

    /**
     * The constructor that sets the attribute values when creating a new Division.
     * @param divisionId sets the divisionId.
     * @param divisionName sets the divisionName.
     * @param createDate sets the createdDate.
     * @param createdBy sets the createdBy.
     * @param lastUpdate sets the lastUpdate.
     * @param lastUpdatedBy sets the lastUpdatedBy.
     * @param countryId sets the countryId.
     */
    public Divisions(int divisionId, String divisionName, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lastUpdatedBy, int countryId) {
        this.divisionId = divisionId;
        DivisionName = divisionName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryId = countryId;
    }
    /**
     * method for returning the divisionId.
     * @return divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }
    /**
     * method for setting the divisionId for the Divisions.
     * @param divisionId sets divisionId
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
    /**
     * method for returning the DivisionName.
     * @return divisionName
     */
    public String getDivisionName() {
        return DivisionName;
    }
    /**
     * method for setting the divisionName for the Divisions.
     * @param divisionName sets divisionName
     */
    public void setDivisionName(String divisionName) {
        DivisionName = divisionName;
    }
    /**
     * method for returning the createDate.
     * @return createDate
     */
    public ZonedDateTime getCreateDate() {
        return createDate;
    }
    /**
     * method for setting the createDate for the Divisions.
     * @param createDate sets createDate
     */
    public void setCreateDate(ZonedDateTime createDate) { this.createDate = createDate; }
    /**
     * method for returning the createdBy.
     * @return createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * method for setting the createdBy for the Divisions.
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
     * method for setting the lastUpdate for the Divisions.
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
     * method for setting the lastUpdatedBy for the Divisions.
     * @param lastUpdatedBy sets lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**
     * method for returning the countryId.
     * @return countryId
     */
    public int getCountryId() {
        return countryId;
    }
    /**
     * method for setting the countryId for the Divisions.
     * @param countryId sets countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    /**
     * This method overrides the toString method used to set an Object to a string.
     * @return DivisionName.
     */
    @Override
    public String toString(){
        return (DivisionName);
    }
}
