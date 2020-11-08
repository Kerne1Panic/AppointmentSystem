package AppointmentSystem.Model;

import java.time.ZonedDateTime;
/**
 * Countries class used to define countries.
 * @author josealvarezpulido
 */
public class Countries {
    /**
     * The ID  of the country.
     */
    private int countryId;
    /**
     * The name of the Country.
     */
    private String name;
    /**
     * The Creation Date of the Country.
     */
    private ZonedDateTime createDate;
    /**
     * The User that created the Country.
     */
    private String createdBy;
    /**
     * The Date that the Country was last updated.
     */
    private ZonedDateTime lastUpdate;
    /**
     * The user that last updated the Country.
     */
    private String lastUpdatedBy;

    /**
     * The constructor that sets the attribute values when creating a new Country.
     * @param countryId sets the  country ID.
     * @param name sets the country name.
     * @param createDate sets  the createDate.
     * @param createdBy sets the createdBy.
     * @param lastUpdate sets teh lastUpdate.
     * @param lastUpdatedBy sets lastUpdateBy.
     */
    public Countries(int countryId, String name, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lastUpdatedBy) {
        this.countryId = countryId;
        this.name = name;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
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
     * method for setting the countryId for the Countries.
     * @param countryId sets countryId
     * */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    /**
     * method for returning the name.
     * @return name
     * */
    public String getName() {
        return name;
    }
    /**
     * method for setting the name for the Countries.
     * @param name sets name
     * */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * method for returning the createDate.
     * @return createDate
     */
    public ZonedDateTime getCreateDate() {
        return createDate;
    }
    /**
     * method for setting the createDate for the Countries.
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
     * method for setting the createdBy for the Countries.
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
     * method for setting the lastUpdate for the Countries.
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
     * method for setting the lastUpdatedBy for the Countries.
     * @param lastUpdatedBy sets lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**
     * This method overrides the toString method used to set an Object to a string.
     * @return name.
     */
    @Override
    public String toString(){
        return (name);
    }
}

