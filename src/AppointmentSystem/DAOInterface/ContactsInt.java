package AppointmentSystem.DAOInterface;

import AppointmentSystem.Model.Contacts;
import javafx.collections.ObservableList;

/**
 * @author josealvarezpulido
 */
public interface ContactsInt {
    static ObservableList<Contacts> getAllContacts() {
        return null;
    }

    void updateContacts();
    void deleteContacts();
    void addContacts();
}
