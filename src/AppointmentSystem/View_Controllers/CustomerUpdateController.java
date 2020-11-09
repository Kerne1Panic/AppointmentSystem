package AppointmentSystem.View_Controllers;

import AppointmentSystem.DAOImp.CountriesImp;
import AppointmentSystem.DAOImp.CustomersImp;
import AppointmentSystem.DAOImp.DivisionsImp;
import AppointmentSystem.LambdaInterfaces.LambdaCountry;
import AppointmentSystem.LambdaInterfaces.LambdaDiv;
import AppointmentSystem.Model.Countries;
import AppointmentSystem.Model.Customers;
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
 * Controller for the CustomerUpdateView.fxml, used to implement action methods and initialize values for the stage. Uses javafx library.
 * @author josealvarezpulido
 */
public class CustomerUpdateController implements Initializable {
    /**
     * a resource bundle that gets the default Locale and the location of the resource bundle used for translation purposes.
     */
    ResourceBundle bundle = ResourceBundle.getBundle("AppointmentSystem/ResourceBundle/Nat", Locale.getDefault());
    /**
     * Customer Update title label.
     */
    @FXML
    private Label titleLabel;
    /**
     * Customer Update name label.
     */
    @FXML
    private Label nameLabel;
    /**
     * Customer Update address label.
     */
    @FXML
    private Label addressLabel;
    /**
     * Customer Update postal label.
     */
    @FXML
    private Label postalLabel;
    /**
     * Customer Update phone label.
     */
    @FXML
    private Label phoneLabel;
    /**
     * Customer Update name field.
     */
    @FXML
    private TextField nameField;
    /**
     * Customer Update address field.
     */
    @FXML
    private TextField addressField;
    /**
     * Customer Update postal field.
     */
    @FXML
    private TextField postalField;
    /**
     * Customer Update phone field.
     */
    @FXML
    private TextField phoneField;
    /**
     * Customer Update Countries comboBox.
     */
    @FXML
    private ComboBox<Countries> countryCombo;
    /**
     * Customer Update Divisions comboBox.
     */
    @FXML
    private ComboBox<Divisions> divisionCombo;
    /**
     * Customer Update countries label.
     */
    @FXML
    private Label countryLabel;
    /**
     * Customer Update divisions label.
     */
    @FXML
    private Label divisionLabel;
    /**
     * Customer Update cancel button.
     */
    @FXML
    private Button cancelButtonText;
    /**
     * Customer Update save button.
     */
    @FXML
    private Button saveButtonText;
    /**
     * Customer Update customer ID label.
     */
    @FXML
    private Label customerIdLabel;
    /**
     * Customer Update customer ID text label.
     */
    @FXML
    private Label customerIdText;
    /**
     * Customer Update error label.
     */
    @FXML
    private Label errorLabel;
    /**
     * customer Update customer ID.
     */
    private int customerID;

    /**
     * Initializes the default values and behaviours for the CustomerUpdateView.fxml file. uses resource bundle to initialize labels so they are in the appropriate language.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Title Text
        titleLabel.setText(bundle.getString("UpdateCustomer"));
        //Label Text
        nameLabel.setText(bundle.getString("CustomerName"));
        addressLabel.setText(bundle.getString("Address"));
        postalLabel.setText(bundle.getString("PostalCode"));
        phoneLabel.setText(bundle.getString("Phone"));
        countryLabel.setText(bundle.getString("Country"));
        divisionLabel.setText(bundle.getString("Division"));
        customerIdLabel.setText(bundle.getString("CustomerId"));
        //Button Text
        saveButtonText.setText(bundle.getString("Save"));
        cancelButtonText.setText(bundle.getString("Cancel"));
        //After Selecting the Country a list of the Divisions for that country are allowed to be selected in Division combo box
        countryCombo.setItems(CountriesImp.getAllCountries());
        //error label
        errorLabel.setText("");
    }

    /**
     * Passed Customer Object from CustomerMenuController.
     * lambda discussion 1 LambdaDiv, this lambda is used to return an a Division Object which is needed to set the Division comboBox,
     * a lambda is perfect for a situation where you need to create and use a method within a method, it is more efficient than creating a separate method just for code that will only be use in this instance.
     * lambda discussion 2 LambdaCountry, this lambda is used to return an a Country Object which is needed to set the Country comboBox,
     * a lambda is perfect for this situation for the same reasons that LambdaDiv was. No need to create a separate method for a method that will only be used in this instance.
     * @param customers sets the Customer Update Form with values passed from the Customer Menu Table view Customer Selection.
     */
    public void CustomerSent(Customers customers) {
        customerID = customers.getCustomerId();
        nameField.setText(customers.getCustomerName());
        addressField.setText(customers.getAddress());
        postalField.setText(customers.getPostalCode());
        phoneField.setText(customers.getPhone());
        customerIdText.setText(String.valueOf(customers.getCustomerId()));
        //LambdaDiv Expression used to return a Division.
        LambdaDiv div = (divId)-> {
          for(Divisions division :  DivisionsImp.getAllDivisions()){
              if(division.getDivisionId() == divId){
                 return division;
              }
          }
          return null;
        };
        //Instance of LambdaDiv used to create a Division object tempDiv.
        Divisions tempDiv = div.getDivision(customers.getDivisionId());
        //LambdaCountry Expression used to return a Country.
        LambdaCountry country = (countryId) ->{
            for(Countries countries : CountriesImp.getAllCountries()){
                if(countries.getCountryId()==countryId){
                    return countries;
                }
            }
            return null;
        };
        if (tempDiv != null){
            ObservableList<Divisions> filteredDivisions = FXCollections.observableArrayList();
            //Instance of LambdaCountry Expression used to create a Country object tempCountry
            Countries tempCountry = country.getCountry(tempDiv.getCountryId());
            if(tempCountry != null){
                //Country and Division ComboBoxes are set.
                countryCombo.setValue(tempCountry);
                divisionCombo.setValue(tempDiv);
                for(Divisions division : DivisionsImp.getAllDivisions()){
                    if(division.getCountryId() == tempCountry.getCountryId()){
                        filteredDivisions.add(division);
                    }
                }
                divisionCombo.setItems(filteredDivisions);
            }
        }
    }

    /**
     * This method will set the Division comboBox, filters the Division Combo box so that only Divisions that have the same country code will be displayed.
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
     * This method is used as an action event for the cancel button, changing the Scene of the Stage.
     * @param event ActionEvent
     * @throws IOException this is an exception handling technique that throws IOExceptions.
     */
    @FXML
    void cancelButton(ActionEvent event) throws IOException
    {
        Parent cancelParent = FXMLLoader.load(getClass().getResource("/AppointmentSystem/View_Controllers/CustomerMenuView.fxml"));
        Scene cancelScene = new Scene(cancelParent);
        Stage cancelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        cancelStage.setScene(cancelScene);
        cancelStage.show();
    }
    /**
     * On action this method sets local variables to the values inputted to the form then goes through try catch and if statements
     * to exception handle null and blank values, Then creates a customer using CustomersImp.updateCustomer(values) which will update the customer in the
     * database. observable list can be called using CustomersImp.getAllCustomers() to extract the customers from the database.
     * @param event ActionEvent.
     */
    @FXML
    void saveButton(ActionEvent event) {
        int customerId = customerID;
        String name = nameField.getText();
        String address = addressField.getText();
        String postalCode = postalField.getText();
        String phone = phoneField.getText();
        try{
            if(!nameField.getText().isBlank() && !addressField.getText().isBlank() && !postalField.getText().isBlank() && !phoneField.getText().isBlank() && divisionCombo.getValue() != null)
            {
                if(divisionCombo.getValue().getCountryId() == countryCombo.getValue().getCountryId())
                {
                    int division = divisionCombo.getValue().getDivisionId();
                    ZoneId zoneId = ZoneId.systemDefault();
                    LocalDateTime update = LocalDateTime.now();
                    LocalDateTime lastUpdated = TimeUtil.convertToUTC(update,zoneId);
                    CustomersImp.updateCustomers(name, address,postalCode,phone, lastUpdated, division,customerId);
                    Parent cancelParent = FXMLLoader.load(getClass().getResource("/AppointmentSystem/View_Controllers/CustomerMenuView.fxml"));
                    Scene cancelScene = new Scene(cancelParent);
                    Stage cancelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    cancelStage.setScene(cancelScene);
                    cancelStage.show();
                }
            }
            else{
                errorLabel.setText(bundle.getString("MissingValues"));
            }
        }catch (Exception e){
            errorLabel.setText(e.getLocalizedMessage());
        }
    }
}
