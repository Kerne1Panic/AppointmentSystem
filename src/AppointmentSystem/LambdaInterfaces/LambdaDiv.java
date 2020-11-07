package AppointmentSystem.LambdaInterfaces;

import AppointmentSystem.Model.Divisions;
/**
 * interface with one abstract expression to demonstrate lambda expressions to implement used for Divisions.
 * A user defined functional interface.
 * @author josealvarezpulido
 */
public interface LambdaDiv {
    /**
     * Abstract method of the Customers type, taking a int.
     * @param id int used for abstract expression.
     * @return Divisions type
     */
    Divisions getDivision(int id);
}
