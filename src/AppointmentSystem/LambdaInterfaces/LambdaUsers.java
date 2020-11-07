package AppointmentSystem.LambdaInterfaces;

import AppointmentSystem.Model.Users;
/**
 * interface with one abstract expression to demonstrate lambda expressions to implement used for Users.
 * A user defined functional interface.
 * @author josealvarezpulido
 */
public interface LambdaUsers {
    /**
     * Abstract method of the Users type, taking a int.
     * @param id int used for abstract expression.
     * @return Users type
     */
    Users getUser(int id);
}
