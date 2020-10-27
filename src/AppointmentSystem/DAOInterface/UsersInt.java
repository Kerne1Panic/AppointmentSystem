package AppointmentSystem.DAOInterface;

import AppointmentSystem.Model.Users;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/**
 * @author josealvarezpulido
 */
public interface UsersInt {
    static ObservableList<Users> getAllUsers() {
        return null;
    }

    static void updateUsers() {

    }

    static void deleteUsers() {

    }

    static void addUsers() {

    }

    static Users getUser(String username) throws SQLException {
        return null;
    }
}
