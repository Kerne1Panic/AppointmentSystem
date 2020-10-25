package AppointmentSystem.DAOImp;

import AppointmentSystem.DAOInterface.AppointmentInt;
import AppointmentSystem.Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author josealvarezpulido
 */
public class AppointmentImp implements AppointmentInt {

    public ObservableList<Appointments> appointments = FXCollections.observableArrayList();

    public AppointmentImp(ObservableList<Appointments> appointments) {
        this.appointments = appointments;
    }

    @Override
    public ObservableList<Appointments> getAllAppointments() {
        return null;
    }

    @Override
    public void updateAppointments() {

    }

    @Override
    public void deleteAppointments() {

    }

    @Override
    public void addAppointments() {

    }
}
