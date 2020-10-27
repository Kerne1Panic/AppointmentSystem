package AppointmentSystem.DAOImp;

import AppointmentSystem.DAOInterface.DivisionsInt;
import AppointmentSystem.Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DivisionsImp implements DivisionsInt {

    public ObservableList<Divisions> divisions = FXCollections.observableArrayList();

    @Override
    public ObservableList<Divisions> getAllDivisions() {
        return null;
    }

    @Override
    public void updateDivisions() {

    }

    @Override
    public void deleteDivisions() {

    }

    @Override
    public void addDivisions() {

    }
}
