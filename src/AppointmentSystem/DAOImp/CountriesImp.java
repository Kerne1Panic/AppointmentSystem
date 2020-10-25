package AppointmentSystem.DAOImp;

import AppointmentSystem.DAOInterface.CountriesInt;
import AppointmentSystem.Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CountriesImp implements CountriesInt {

    public ObservableList<Countries> countries = FXCollections.observableArrayList();

    public CountriesImp(ObservableList<Countries> countries) {
        this.countries = countries;
    }

    @Override
    public ObservableList<Countries> getAllCountries() {
        return null;
    }

    @Override
    public void updateCountries() {

    }

    @Override
    public void deleteCountries() {

    }

    @Override
    public void addCountries() {

    }
}
