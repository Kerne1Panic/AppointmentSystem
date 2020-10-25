package AppointmentSystem.DAOInterface;

import AppointmentSystem.Model.Users;
import javafx.collections.ObservableList;

/**
 * @author josealvarezpulido
 */
public interface UsersInt {
    public ObservableList<Users> getAllUsers();
    public void updateUsers();
    public void deleteUsers();
    public void addUsers();
}
