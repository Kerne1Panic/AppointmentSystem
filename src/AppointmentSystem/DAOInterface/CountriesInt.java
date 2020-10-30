package AppointmentSystem.DAOInterface;

import AppointmentSystem.Model.Countries;
import javafx.collections.ObservableList;

/**
 * @author josealvarezpulido
 */
public interface CountriesInt {
    static ObservableList<Countries> getAllCountries() {
        return null;
    }

    void updateCountries();
    void deleteCountries();
    void addCountries();
}
