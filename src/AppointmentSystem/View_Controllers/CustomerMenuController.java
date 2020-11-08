package AppointmentSystem.View_Controllers;

import AppointmentSystem.DAOImp.AppointmentImp;
import AppointmentSystem.DAOImp.CustomersImp;
import AppointmentSystem.Model.Appointments;
import AppointmentSystem.Model.Customers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller for the CustomerMenuView.fxml, used to implement action methods and initialize values for the stage. Uses javafx library.
 * @author josealvarezpulido
 */
public class CustomerMenuController implements Initializable {
    /**
     * a resource bundle that gets the default Locale and the location of the resource bundle used for translation purposes.
     */
    ResourceBundle bundle = ResourceBundle.getBundle("AppointmentSystem/ResourceBundle/Nat",Locale.getDefault());
    /**
     * Customer Table View.
     */
    @FXML
    private TableView<Customers> customerTable;
    /**
     * customer ID  column in Customer  Table.
     */
    @FXML
    private TableColumn<Customers, Integer> customerIdCol;
    /**
     * customer name column in Customer Table.
     */
    @FXML
    private TableColumn<Customers, String> nameCol;
    /**
     * customer address column in Customer Table.
     */
    @FXML
    private TableColumn<Customers, String> addressCol;
    /**
     * customer postal code column in Customer Table.
     */
    @FXML
    private TableColumn<Customers,String> postalCol;
    /**
     * customer phone column  in Customer Table.
     */
    @FXML
    private TableColumn<Customers, String> phoneCol;
    /**
     * customer created date  column in Customer Table.
     */
    @FXML
    private TableColumn<Customers, ZonedDateTime> createdCol;
    /**
     * customer updated column in Customer Table.
     */
    @FXML
    private TableColumn<Customers,ZonedDateTime> updatedCol;
    /**
     * customer  division name column in Customer Table.
     */
    @FXML
    private TableColumn<Customers,String> divisionCol;
    /**
     * customer title label.
     */
    @FXML
    private Label titleLabel;
    /**
     * add button.
     */
    @FXML
    private Button addButton;
    /**
     * update button.
     */
    @FXML
    private Button updateButton;
    /**
     * remove button
     */
    @FXML
    private Button removeButton;
    /**
     * cancel button
     */
    @FXML
    private Button cancelButton;
    /**
     * error label.
     */
    @FXML
    private Label errorLabel;

    /**
     * Initializes the default values and behaviours for the CustomerMenuView.fxml file. uses resource bundle to initialize labels so they are in the appropriate language.
     * The Table is set using the CustomerImp Class' static method getAllCustomers which returns an observable list of the data from the database.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //setting Property values to the columns
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        createdCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        updatedCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        //customer table, set by getAllCustomers.
        customerTable.setItems(CustomersImp.getAllCustomers());
        //Title Text
        titleLabel.setText(bundle.getString("CustomerMenu"));
        //Button Text
        addButton.setText(bundle.getString("Add"));
        updateButton.setText(bundle.getString("Update"));
        cancelButton.setText(bundle.getString("Cancel"));
        removeButton.setText(bundle.getString("Remove"));
        //Table Columns Text
        customerIdCol.setText(bundle.getString("CustomerId"));
        nameCol.setText(bundle.getString("Name"));
        addressCol.setText(bundle.getString("Address"));
        postalCol.setText(bundle.getString("PostalCode"));
        phoneCol.setText(bundle.getString("Phone"));
        createdCol.setText(bundle.getString("Created"));
        updatedCol.setText(bundle.getString("Updated"));
        divisionCol.setText(bundle.getString("Division"));
    }

    /**
     * This method is used as an action event for the add customer button, changing the Scene of the Stage.
     * @param event ActionEvent
     * @throws IOException this is an exception handling technique that throws IOExceptions.
     */
    @FXML
    void addCustomerButton(ActionEvent event) throws IOException
    {
        Parent addParent = FXMLLoader.load(getClass().getResource("/AppointmentSystem/View_Controllers/CustomerAddView.fxml"));
        Scene addScene = new Scene(addParent);
        Stage addStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addStage.setScene(addScene);
        addStage.show();
    }

    /**
     * This method removes customers from the database.
     * When deleting a customer, all appointments for the specific customer must be deleted first, due to foreign key constraints.
     * Conformation message is  displayed to make sure user  intends to delete. Information Message is  displayed after  customer has been deleted.
     * @param event ActionEvent
     */
    @FXML
    void removeCustomerButton(ActionEvent event)
    {
        errorLabel.setText("");
        if(customerTable.getSelectionModel().getSelectedItem() != null)
        {
            Customers customers = customerTable.getSelectionModel().getSelectedItem();
            String customerName = customers.getCustomerName();
            int customerId = customerTable.getSelectionModel().getSelectedItem().getCustomerId();
            //Alert Message before deleting customer.
            Alert deleteCustomer = new Alert(Alert.AlertType.CONFIRMATION, bundle.getString("DeleteCustomer")+ ": " +customerName);
            Optional<ButtonType> result = deleteCustomer.showAndWait();
            if(result.get() == ButtonType.OK){
                for(Appointments appointments: AppointmentImp.getAllAppointments()) {
                    if (appointments.getCustomerId() == customerId) {
                        AppointmentImp.deleteAppointments(appointments.getAppointmentId());
                    }
                }
                //Alert Message when customer has been deleted.
                Alert customerDeleted = new Alert(Alert.AlertType.INFORMATION,customerName + " "+ bundle.getString("customerDeleted"));
                CustomersImp.deleteCustomers(customerId);
                customerDeleted.showAndWait();
                //customer table, set by getAllCustomers. refreshes the Table.
                customerTable.setItems(CustomersImp.getAllCustomers());
            }
        }
        else {
            errorLabel.setText(bundle.getString("NothingSelected"));
        }
    }

    /**
     * This method is used as an action event for the add customer button, changing the Scene of the Stage.
     * Customer is sent to CustomerUpdateView.fxml using loader to load resources and  used the controller to send over the selected Item using a  method from the CustomerUpdateController.
     * @param event ActionEvent
     * @throws IOException this is an exception handling technique that throws IOExceptions.
     */
    @FXML
    void updateCustomerButton(ActionEvent event) throws IOException
    {
        errorLabel.setText("");
        if(customerTable.getSelectionModel().getSelectedItem() != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/AppointmentSystem/View_Controllers/CustomerUpdateView.fxml"));
            loader.load();
            CustomerUpdateController controller = loader.getController();
            Customers customersSent = customerTable.getSelectionModel().getSelectedItem();
            controller.CustomerSent(customersSent);
            Parent update = loader.getRoot();
            Stage updateStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene updateScene = new Scene(update);
            updateStage.setScene(updateScene);
            updateStage.show();
        }
        else{
            errorLabel.setText(bundle.getString("NothingSelected"));
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
        Parent cancelParent = FXMLLoader.load(getClass().getResource("/AppointmentSystem/View_Controllers/MainMenuView.fxml"));
        Scene cancelScene = new Scene(cancelParent);
        Stage cancelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        cancelStage.setScene(cancelScene);
        cancelStage.show();
    }
}
