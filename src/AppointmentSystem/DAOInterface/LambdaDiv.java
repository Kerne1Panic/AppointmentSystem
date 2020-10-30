package AppointmentSystem.DAOInterface;

import AppointmentSystem.Model.Divisions;

public interface LambdaDiv {
    //This abstract method returns The Division.
    Divisions getDivision(int id);
}
