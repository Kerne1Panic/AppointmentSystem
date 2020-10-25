package AppointmentSystem.DAOInterface;

import AppointmentSystem.Model.Divisions;
import javafx.collections.ObservableList;

/**
 * @author josealvarezpulido
 */
public interface DivisionsInt {
    public ObservableList<Divisions> getAllDivisions();
    public void updateDivisions();
    public void deleteDivisions();
    public void addDivisions();
}
