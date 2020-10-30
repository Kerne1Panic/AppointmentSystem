package AppointmentSystem.DAOInterface;

import AppointmentSystem.Model.Appointments;
import javafx.collections.ObservableList;

/**
 * @author josealvarezpulido
 */
public interface AppointmentInt {
    static ObservableList<Appointments> getAllAppointments() {
        return null;
    }

    void updateAppointments();
    void deleteAppointments();
    void addAppointments();
}
