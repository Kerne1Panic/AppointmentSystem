package AppointmentSystem.DAOImp;

import AppointmentSystem.Model.Contacts;
import AppointmentSystem.Utilities.QueryUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ContactsImp {

    static ObservableList<Contacts> contacts = FXCollections.observableArrayList();


    public static ObservableList<Contacts> getAllContacts() {
        final String sqlStatement = "SELECT * FROM contacts";
        if(contacts != null){
            contacts.clear();
        }
        try {
            QueryUtil.setPreparedStatement(sqlStatement);
            PreparedStatement ps = QueryUtil.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
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


    public void updateContacts() {

    }

    public void deleteContacts() {

    }

    public void addContacts() {

    }
}
