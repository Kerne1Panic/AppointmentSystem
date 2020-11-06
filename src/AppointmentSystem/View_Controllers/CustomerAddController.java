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

    @FXML
    private Label titleLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label postalLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private TextField nameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField postalField;

    @FXML
    private TextField phoneField;

    @FXML
    private ComboBox<Countries> countryCombo;

    @FXML
    private ComboBox<Divisions> divisionCombo;

    @FXML
    private Label countryLabel;

    @FXML
    private Label divisionLabel;

    @FXML
    private Button cancelButtonText;

    @FXML
    private Button saveButtonText;

    @FXML
    private Label errorLabel;

    /**
     * Initializes the default values and behaviours for the CustomerAddView.fxml file. uses resource bundle to initialize labels so they are in the appropriate language.
     * @param url
     * @param resourceBundle
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
     * Filters the Division Combo box so that only Divisions that have the same country ID will be displayed.
     */
    public void CountryComboSelect(){
        errorLabel.setText("");
        ObservableList<Divisions> divisionsList = DivisionsImp.getAllDivisions();
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
     * This method is used to navigate back to the CustomerMenuView.fxml, setting a new stage and showing it.
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
     *
     * @param event
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
