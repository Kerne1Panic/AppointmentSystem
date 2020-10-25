package AppointmentSystem.DAOInterface;

import AppointmentSystem.Model.Appointments;
import javafx.collections.ObservableList;

/**
 * @author josealvarezpulido
 */
public interface AppointmentInt {
    public ObservableList<Appointments> getAllAppointments();
    public void updateAppointments();
    public void deleteAppointments();
    public void addAppointments();
}
