package AppointmentSystem.LambdaInterfaces;

import AppointmentSystem.Model.Contacts;
/**
 * interface with one abstract expression to demonstrate lambda expressions to implement used for Contacts.
 * A user defined functional interface.
 * @author josealvarezpulido
 */
public interface LambdaContacts {
    /**
     * Abstract method of the Contacts type, taking a int.
     * @param id int used for abstract expression.
     * @return Contacts type
     */
    Contacts getContact(int id);
}
