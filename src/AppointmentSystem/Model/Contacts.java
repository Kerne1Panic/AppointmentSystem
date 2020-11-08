package AppointmentSystem.Model;
/**
 * Contacts class used to define Contacts.
 * @author josealvarezpulido
 */
public class Contacts {
    /**
     * The ID of the contact.
     */
    private int contactId;
    /**
     * The name of the contact.
     */
    private String contactName;
    /**
     * the email of the contact.
     */
    private String email;

    /**
     * The constructor that sets the attribute values when creating a new Contact.
     * @param contactId sets the contactId
     * @param contactName sets the contactName
     * @param email sets the email*/
    public Contacts(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * method for returning the contactId.
     * @return contactId
     */
    public int getContactId() {
        return contactId;
    }
    /**
     * method for setting the contactId for the contact.
     * @param contactId sets contactId
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    /**
     * method for returning the contactName.
     * @return contactName
     */
    public String getContactName() {
        return contactName;
    }
    /**
     * method for setting the contactName for the contact.
     * @param contactName sets contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    /**
     * method for returning the email.
     * @return email
     */
    public String getEmail() {
        return email;
    }
    /**
     * method for setting the email for the contact.
     * @param email sets email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * This method overrides the toString method used to set an Object to a string.
     * @return contactName.
     */
    @Override
    public String toString(){
        return (contactName);
    }
}
