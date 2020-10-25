package AppointmentSystem.DAOImp;

import AppointmentSystem.DAOInterface.CustomersInt;
import AppointmentSystem.Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomersImp implements CustomersInt {

    public ObservableList<Customers> customers = FXCollections.observableArrayList();

    public CustomersImp(ObservableList<Customers> customers) {
        this.customers = customers;
    }

    @Override
    public ObservableList<Customers> getAllCustomers() {
        return null;
    }

    @Override
    public void updateCustomers() {

    }

    @Override
    public void deleteCustomers() {

    }

    @Override
    public void addCustomers() {

    }
}
