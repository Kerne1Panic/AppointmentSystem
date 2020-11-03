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
 * @author josealvarezpulido
 * Controller for the Customer menu view.
 */
public class CustomerMenuController implements Initializable {
    //Resource Bundle used for changing languages
    ResourceBundle bundle = ResourceBundle.getBundle("AppointmentSystem/ResourceBundle/Nat",Locale.getDefault());

    @FXML
    private TableView<Customers> customerTable;

    @FXML
    private TableColumn<Customers, Integer> customerIdCol;

    @FXML
    private TableColumn<Customers, String> nameCol;

    @FXML
    private TableColumn<Customers, String> addressCol;

    @FXML
    private TableColumn<Customers,String> postalCol;

    @FXML
    private TableColumn<Customers, String> phoneCol;

    @FXML
    private TableColumn<Customers, ZonedDateTime> createdCol;

    @FXML
    private TableColumn<Customers,ZonedDateTime> updatedCol;

    @FXML
    private TableColumn<Customers,String> divisionCol;

    @FXML
    private Label titleLabel;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label errorLabel;

    /**
     * initializes the labels, column names, and button text, sets Property values for the columns so that they could be receive the data from the database.
     * The Table is set using the CustomerImp Class' static method getAllCustomers which returns an observable list of the data from the database.
     * @param url
     * @param resourceBundle
     *
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
     * @param event
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
     * This method removes customers from the database. On a delete cascade delete appointments.
     * When deleting a customer, all appointments must be deleted first, due to foreign key constraints.
     * @param event
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
            Alert deleteCustomer = new Alert(Alert.AlertType.CONFIRMATION, bundle.getString("DeleteCustomer")+ ": " +customerName);
            Optional<ButtonType> result = deleteCustomer.showAndWait();
            if(result.get() == ButtonType.OK){
                for(Appointments appointments: AppointmentImp.getAllAppointments()) {
                    if (appointments.getCustomerId() == customerId) {
                        AppointmentImp.deleteAppointments(appointments.getAppointmentId());
                    }
                }
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
     * @param event
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
     * @param event
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
