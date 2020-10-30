package AppointmentSystem.DAOInterface;

import AppointmentSystem.Model.Divisions;
import javafx.collections.ObservableList;

/**
 * @author josealvarezpulido
 */
public interface DivisionsInt {

    static ObservableList<Divisions> getAllDivisions() {
        return null;
    }

    void updateDivisions();
    void deleteDivisions();
    void addDivisions();
}
