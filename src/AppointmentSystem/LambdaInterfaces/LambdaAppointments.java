package AppointmentSystem.LambdaInterfaces;

import AppointmentSystem.Model.Appointments;
import javafx.collections.ObservableList;

import java.time.ZonedDateTime;

public interface LambdaAppointments {
    Appointments getAppointments (ZonedDateTime appointmentTime);
}
