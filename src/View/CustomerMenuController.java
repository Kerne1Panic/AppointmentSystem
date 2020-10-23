package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author josealvarezpulido
 * Controller for the Customer menu view.
 */
public class CustomerMenuController implements Initializable {
    //Resource Bundle used for changing languages
    ResourceBundle bundle = ResourceBundle.getBundle("ResourceBundle/Nat",Locale.getDefault());

    @FXML
    private TableView<?> customerTable;

    @FXML
    private TableColumn<?, ?> customerIdCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> addressCol;

    @FXML
    private TableColumn<?, ?> postalCol;

    @FXML
    private TableColumn<?,?> phoneCol;

    @FXML
    private TableColumn<?, ?> createdCol;

    @FXML
    private TableColumn<?, ?> updatedCol;

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

    /**
     *
     * @param url
     * @param resourceBundle
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
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





    }
    /**
     * This method is used as an action event for the add customer button, changing the Scene of the Stage.
     * @param event
     * @throws IOException this is an exception handling technique that throws IOExceptions.
     */
    @FXML
    void addCustomerButton(ActionEvent event) throws IOException
    {
        Parent addParent = FXMLLoader.load(getClass().getResource("/View/CustomerAddView.fxml"));
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

    }


    /**
     * This method is used as an action event for the add customer button, changing the Scene of the Stage.
     * @param event
     * @throws IOException this is an exception handling technique that throws IOExceptions.
     */
    @FXML
    void updateCustomerButton(ActionEvent event) throws IOException
    {
        Parent updateParent = FXMLLoader.load(getClass().getResource("/View/CustomerUpdateView.fxml"));
        Scene updateScene = new Scene(updateParent);
        Stage updateStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        updateStage.setScene(updateScene);
        updateStage.show();
    }

    /**
     * This method is used as an action event for the cancel button, changing the Scene of the Stage.
     * @param event
     * @throws IOException this is an exception handling technique that throws IOExceptions.
     */
    @FXML
    void cancelButton(ActionEvent event) throws IOException
    {
        Parent cancelParent = FXMLLoader.load(getClass().getResource("/View/MainMenuView.fxml"));
        Scene cancelScene = new Scene(cancelParent);
        Stage cancelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        cancelStage.setScene(cancelScene);
        cancelStage.show();
    }

}
