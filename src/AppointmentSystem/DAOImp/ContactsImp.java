package AppointmentSystem.DAOImp;

import AppointmentSystem.Model.Contacts;
import AppointmentSystem.Utilities.QueryUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * @author josealvarezpulido
 * Used to perform the Read operations with the data base of the Contact Class Model.
 */
public class ContactsImp {
    /**
     * List of all contacts extracted from the database, it is set and returned in the getAllContacts static method.
     */
    static ObservableList<Contacts> contacts = FXCollections.observableArrayList();

    /**
     * Read, the static method used to read the data from the database and create  Contacts model object.
     * @return ObservableList contacts
     */
    public static ObservableList<Contacts> getAllContacts() {
        /**
         * sqlStatement is a string that contains the SQL statement that will be executed.
         */
        final String sqlStatement = "SELECT * FROM contacts";
        if(contacts != null){
            contacts.clear();
        }
        /**
         * Try Catch block used for catching exceptions that may be thrown.
         */
        try {
            QueryUtil.setPreparedStatement(sqlStatement);
            PreparedStatement ps = QueryUtil.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            //while next() loop used to loop through the database
            while (rs.next())
            {
                //values for contacts
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                Contacts contactsFound = new Contacts(contactId,contactName,email);
                contacts.add(contactsFound);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return contacts;
    }

}
