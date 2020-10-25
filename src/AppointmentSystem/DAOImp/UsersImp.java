package AppointmentSystem.DAOImp;

import AppointmentSystem.DAOInterface.UsersInt;
import AppointmentSystem.Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UsersImp implements UsersInt {

    public ObservableList<Users> users = FXCollections.observableArrayList();

    public UsersImp(ObservableList<Users> users) {
        this.users = users;
    }

    @Override
    public ObservableList<Users> getAllUsers() {
        return null;
    }

    @Override
    public void updateUsers() {

    }

    @Override
    public void deleteUsers() {

    }

    @Override
    public void addUsers() {

    }
}
