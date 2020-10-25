package AppointmentSystem.DAOInterface;

import AppointmentSystem.Model.Countries;
import javafx.collections.ObservableList;

/**
 * @author josealvarezpulido
 */
public interface CountriesInt {
    public ObservableList<Countries> getAllCountries();
    public void updateCountries();
    public void deleteCountries();
    public void addCountries();
}
