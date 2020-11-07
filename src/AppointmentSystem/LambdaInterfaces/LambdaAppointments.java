package AppointmentSystem.LambdaInterfaces;

import AppointmentSystem.Model.Appointments;

import java.time.ZonedDateTime;
/**
 * interface with one abstract expression to demonstrate lambda expressions to implement used for Appointments.
 * A user defined functional interface.
 * @author josealvarezpulido
 */
public interface LambdaAppointments {
    /**
     * Abstract method of the Appointments type, taking a ZonedDateTime.
     * @param appointmentTime ZonedDateTime used for abstract expression.
     * @return Appointments type
     */
    Appointments getAppointments (ZonedDateTime appointmentTime);
}
