package AppointmentSystem.DAOImp;

import AppointmentSystem.DAOInterface.ContactsInt;
import AppointmentSystem.Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactsImp implements ContactsInt {

    public ObservableList<Contacts> contacts = FXCollections.observableArrayList();

    public ContactsImp(ObservableList<Contacts> contacts) {
        this.contacts = contacts;
    }

    @Override
    public ObservableList<Contacts> getAllContacts() {
        return null;
    }

    @Override
    public void updateContacts() {

    }

    @Override
    public void deleteContacts() {

    }

    @Override
    public void addContacts() {

    }
}
