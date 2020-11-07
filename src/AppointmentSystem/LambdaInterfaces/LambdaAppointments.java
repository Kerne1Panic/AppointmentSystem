package AppointmentSystem.LambdaInterfaces;

import AppointmentSystem.Model.Appointments;

import java.time.ZonedDateTime;

public interface LambdaAppointments {
    Appointments getAppointments (ZonedDateTime appointmentTime);
}
