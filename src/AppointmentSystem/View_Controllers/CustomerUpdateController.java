package AppointmentSystem.View_Controllers;

import AppointmentSystem.DAOImp.CountriesImp;
import AppointmentSystem.DAOImp.CustomersImp;
import AppointmentSystem.DAOImp.DivisionsImp;
import AppointmentSystem.DAOImp.UsersImp;
import AppointmentSystem.DAOInterface.LambdaCountry;
import AppointmentSystem.DAOInterface.LambdaDiv;
import AppointmentSystem.Model.Countries;
import AppointmentSystem.Model.Customers;
import AppointmentSystem.Model.Divisions;
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
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author josealvarezpulido
 */
public class CustomerUpdateController implements Initializable {
    //Resource Bundle used for changing languages
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
    private Label customerIdLabel;

    @FXML
    private Label customerIdText;

    private int customerID;

    /**
     *
     * @param url
     * @param resourceBundle
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
    }

    /**
     *
     * @param customers
     */
    public void CustomerSent(Customers customers) {
        customerID = customers.getCustomerId();
        nameField.setText(customers.getCustomerName());
        addressField.setText(customers.getAddress());
        postalField.setText(customers.getPostalCode());
        phoneField.setText(customers.getPhone());
        customerIdText.setText(String.valueOf(customers.getCustomerId()));
        /**
         * LambdaDiv
         */
        LambdaDiv div = (divId)-> {
          for(Divisions division :  DivisionsImp.getAllDivisions()){
              if(division.getDivisionId() == divId){
                 return division;
              }
          }
          return null;
        };

        Divisions tempDiv = div.getDivision(customers.getDivisionId());
        /**
         * LambdaCountry
         */
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
            Countries tempCountry = country.getCountry(tempDiv.getCountryId());
            if(tempCountry != null){
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
     * Filters the Division Combo box so that only Divisions that have the same country code will be displayed.
     */
    public void CountryComboSelect(){
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
        }
    }

    /**
     * This method is used as an action event for the cancel button, changing the Scene of the Stage.
     * @param event
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
     *
     * @param event
     */
    @FXML
    void saveButton(ActionEvent event) throws IOException {
        int customerId = customerID;
        String name = nameField.getText();
        String address = addressField.getText();
        String postalCode = postalField.getText();
        String phone = phoneField.getText();
        String updatedBy = UsersImp.getUserLoggedIn();
        int division = divisionCombo.getValue().getDivisionId();
        if(nameField.getText() != null && addressField.getText() != null && postalField.getText() != null && phoneField.getText() != null && divisionCombo.getValue() != null && countryCombo.getValue() != null){
            if(divisionCombo.getValue().getCountryId() == countryCombo.getValue().getCountryId())
            {
                CustomersImp.updateCustomers(name, address,postalCode,phone,division,customerId);

                Parent cancelParent = FXMLLoader.load(getClass().getResource("/AppointmentSystem/View_Controllers/CustomerMenuView.fxml"));
                Scene cancelScene = new Scene(cancelParent);
                Stage cancelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                cancelStage.setScene(cancelScene);
                cancelStage.show();
            }
            else{
                System.out.println("Country Division miss match");
            }

        }
    }

}
