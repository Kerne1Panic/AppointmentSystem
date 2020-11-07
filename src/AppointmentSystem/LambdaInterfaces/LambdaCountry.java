package AppointmentSystem.LambdaInterfaces;

import AppointmentSystem.Model.Countries;
/**
 * interface with one abstract expression to demonstrate lambda expressions to implement used for Countries.
 * A user defined functional interface.
 * @author josealvarezpulido
 */
public interface LambdaCountry {
    /**
     * Abstract method of the Countries type, taking a int.
     * @param id int used for abstract expression.
     * @return Countries type
     */
    Countries getCountry(int id);
}
