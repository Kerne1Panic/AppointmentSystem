package AppointmentSystem.View_Controllers;

import AppointmentSystem.DAOImp.CountriesImp;
import AppointmentSystem.DAOImp.CustomersImp;
import AppointmentSystem.DAOImp.DivisionsImp;
import AppointmentSystem.Model.Countries;
import AppointmentSystem.Model.Divisions;
import AppointmentSystem.Utilities.TimeUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controller for the CustomerAddView.fxml, used to implement action methods and initialize values for the stage. Uses javafx library.
 * @author josealvarezpulido
 */
public class CustomerAddController implements Initializable {
    /**
     * a resource bundle that gets the default Locale and the location of the resource bundle used for translation purposes.
     */
    ResourceBundle bundle = ResourceBundle.getBundle("AppointmentSystem/ResourceBundle/Nat", Locale.getDefault());
    /**
     * Customer Add Title label.
     */
    @FXML
    private Label titleLabel;
    /**
     * Customer Add name label.
     */
    @FXML
    private Label nameLabel;
    /**
     * Customer Add address label.
     */
    @FXML
    private Label addressLabel;
    /**
     * Customer Add postal label.
     */
    @FXML
    private Label postalLabel;
    /**
     * Customer Add phone label.
     */
    @FXML
    private Label phoneLabel;
    /**
     * Customer Add name field.
     */
    @FXML
    private TextField nameField;
    /**
     * Customer Add address field.
     */
    @FXML
    private TextField addressField;
    /**
     * Customer Add postal field.
     */
    @FXML
    private TextField postalField;
    /**
     * Customer Add phone field.
     */
    @FXML
    private TextField phoneField;
    /**
     * Customer Add Countries comboBox.
     */
    @FXML
    private ComboBox<Countries> countryCombo;
    /**
     * Customer Add Divisions comboBox.
     */
    @FXML
    private ComboBox<Divisions> divisionCombo;
    /**
     * Customer Add countries label.
     */
    @FXML
    private Label countryLabel;
    /**
     * Customer Add divisions label.
     */
    @FXML
    private Label divisionLabel;
    /**
     * Customer Add cancel button.
     */
    @FXML
    private Button cancelButtonText;
    /**
     * Customer Add save button.
     */
    @FXML
    private Button saveButtonText;
    /**
     * Customer Add error label.
     */
    @FXML
    private Label errorLabel;

    /**
     * Initializes the default values and behaviours for the CustomerAddView.fxml file. uses resource bundle to initialize labels so they are in the appropriate language.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Title Text
        titleLabel.setText(bundle.getString("NewCustomer"));
        //Label Text
        nameLabel.setText(bundle.getString("CustomerName"));
        addressLabel.setText(bundle.getString("Address"));
        postalLabel.setText(bundle.getString("PostalCode"));
        phoneLabel.setText(bundle.getString("Phone"));
        countryLabel.setText(bundle.getString("Country"));
        divisionLabel.setText(bundle.getString("Division"));
        //Button Text
        saveButtonText.setText(bundle.getString("Save"));
        cancelButtonText.setText(bundle.getString("Cancel"));
        //After Selecting the Country a list of the Divisions for that country are allowed to be selected in Division combo box
        countryCombo.setItems(CountriesImp.getAllCountries());
        //error label
        errorLabel.setText("");
    }
    /**
     * This method will set the Division comboBox, filters the Division Combo box so that only Divisions that have the same country ID will be displayed.
     */
    public void CountryComboSelect(){
        errorLabel.setText("");
        ObservableList<Divisions> divisionsList = DivisionsImp.getAllDivisions();
        //List of the filtered divisions by country.
        ObservableList<Divisions> filteredDivisions = FXCollections.observableArrayList();
        if (countryCombo.getValue() != null){
            int countryId = countryCombo.getValue().getCountryId();
            for(Divisions division : divisionsList){
                if(division.getCountryId() == countryId){
                    filteredDivisions.add(division);
                }
            }
            divisionCombo.setItems(filteredDivisions);
            divisionCombo.setValue(null);
        }
    }
    /**
     * Navigates back to the CustomerMenuView.fxml, setting a new stage and showing it.
     * @param event on User button ActionEvent
     * @throws IOException this is an exception handling technique that throws IOExceptions.
     */
    @FXML
    void cancelButton(ActionEvent event) throws IOException{
        Parent cancelParent = FXMLLoader.load(getClass().getResource("/AppointmentSystem/View_Controllers/CustomerMenuView.fxml"));
        Scene cancelScene = new Scene(cancelParent);
        Stage cancelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        cancelStage.setScene(cancelScene);
        cancelStage.show();
    }
    /**
     * On action this method sets local variables to the values inputted to the form then goes through try catch and if statements
     * to exception handle null and blank values, Then creates a customer using CustomersImp.addCustomer(values) which will add the customer to the
     * database. observable list can be called using CustomersImp.getAllCustomers() to extract the customers from the database.
     * @param event ActionEvent.
     */
    @FXML
    void saveButton(ActionEvent event) {
        String name = nameField.getText();
        String address = addressField.getText();
        String postalCode = postalField.getText();
        String phone = phoneField.getText();
        try {
            if(!nameField.getText().isBlank() && !addressField.getText().isBlank() && divisionCombo.getValue() != null && !postalField.getText().isBlank() && !phoneField.getText().isBlank()){
                if(divisionCombo.getValue().getCountryId() == countryCombo.getValue().getCountryId()) {
                    int divisionId = divisionCombo.getValue().getDivisionId();
                    ZoneId myZoneId = ZoneId.systemDefault();
                    LocalDateTime create = LocalDateTime.now();
                    //localDateTime in UTC
                    LocalDateTime createDate = TimeUtil.convertToUTC(create, myZoneId);
                    CustomersImp.addCustomers(name, address, postalCode, phone, createDate, divisionId);
                    Parent cancelParent = FXMLLoader.load(getClass().getResource("/AppointmentSystem/View_Controllers/CustomerMenuView.fxml"));
                    Scene cancelScene = new Scene(cancelParent);
                    Stage cancelStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    cancelStage.setScene(cancelScene);
                    cancelStage.show();
                }
            }
            else {
                errorLabel.setText(bundle.getString("MissingValues"));
            }
        }catch (Exception e){
            errorLabel.setText(e.getLocalizedMessage());
        }
    }
}
