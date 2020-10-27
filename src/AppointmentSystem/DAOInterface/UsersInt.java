package AppointmentSystem.DAOInterface;

import AppointmentSystem.Model.Users;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/**
 * @author josealvarezpulido
 */
public interface UsersInt {
    public ObservableList<Users> getAllUsers();
    public void updateUsers();
    public void deleteUsers();
    public void addUsers();

    static Users getUser(String username) throws SQLException {
        return null;
    }
}
