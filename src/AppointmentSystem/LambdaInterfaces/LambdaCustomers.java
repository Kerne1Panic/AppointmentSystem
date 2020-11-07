package AppointmentSystem.LambdaInterfaces;

import AppointmentSystem.Model.Customers;
/**
 * interface with one abstract expression to demonstrate lambda expressions to implement used for Customers.
 * A user defined functional interface.
 * @author josealvarezpulido
 */
public interface LambdaCustomers {
    /**
     * Abstract method of the Customers type, taking a int.
     * @param id int used for abstract expression.
     * @return Customers type
     */
    Customers getCustomer(int id);
}
