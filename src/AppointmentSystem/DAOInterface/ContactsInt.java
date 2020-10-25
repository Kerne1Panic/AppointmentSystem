package AppointmentSystem.DAOInterface;

import AppointmentSystem.Model.Contacts;
import javafx.collections.ObservableList;

/**
 * @author josealvarezpulido
 */
public interface ContactsInt {
    public ObservableList<Contacts> getAllContacts();
    public void updateContacts();
    public void deleteContacts();
    public void addContacts();
}
