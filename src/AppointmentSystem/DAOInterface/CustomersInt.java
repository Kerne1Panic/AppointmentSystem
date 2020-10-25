package AppointmentSystem.DAOInterface;

import AppointmentSystem.Model.Customers;
import javafx.collections.ObservableList;

/**
 * @author josealvarezpulido
 */
public interface CustomersInt {
    public ObservableList<Customers> getAllCustomers();
    public void updateCustomers();
    public void deleteCustomers();
    public void addCustomers();
}
