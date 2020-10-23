package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author josealvarezpulido
 */
public class ScheduleMenuController implements Initializable {
    //Resource Bundle used for changing languages
    ResourceBundle bundle = ResourceBundle.getBundle("ResourceBundle/Nat", Locale.getDefault());

    @FXML
    private TableView<?> appointmentTable;

    @FXML
    private TableColumn<?, ?> contactCol;

    @FXML
    private TableColumn<?, ?> appointmentIdCol;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private TableColumn<?, ?> descriptionCol;

    @FXML
    private TableColumn<?, ?> customerIDCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private TableColumn<?, ?> startCol;
    @FXML
    private TableColumn<?, ?> endCol;
    @FXML
    private TableColumn<?, ?> locationCol;

    @FXML
    private Label titleLabel;

    @FXML
    private Button addButtonText;

    @FXML
    private Button updateButtonText;

    @FXML
    private Button removeButtonText;

    @FXML
    private Button cancelButtonText;

    @FXML
    private RadioButton noFilterRadio;

    @FXML
    private RadioButton weeklyRadio;

    @FXML
    private RadioButton monthlyRadio;

    /**
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Title Text
        titleLabel.setText(bundle.getString("AppointmentTitle"));
        //Button Text
        addButtonText.setText(bundle.getString("Add"));
        updateButtonText.setText(bundle.getString("Update"));
        removeButtonText.setText(bundle.getString("Remove"));
        cancelButtonText.setText(bundle.getString("Cancel"));
        //Radio Button Text
        noFilterRadio.setText(bundle.getString("NoFilter"));
        weeklyRadio.setText(bundle.getString("Weekly"));
        monthlyRadio.setText(bundle.getString("Monthly"));
        //Column Text
        contactCol.setText(bundle.getString("ContactName"));
        appointmentIdCol.setText(bundle.getString("AppointmentId"));
        titleCol.setText(bundle.getString("Title"));
        descriptionCol.setText(bundle.getString("Description"));
        customerIDCol.setText(bundle.getString("CustomerId"));
        typeCol.setText(bundle.getString("Type"));
        startCol.setText(bundle.getString("Start"));
        endCol.setText(bundle.getString("End"));
        locationCol.setText(bundle.getString("Location"));

    }

    /**
     *
     * @param event
     */
    @FXML
    void addButton(ActionEvent event) throws IOException
    {
        Parent addParent = FXMLLoader.load(getClass().getResource("/View/ScheduleAddView.fxml"));
        Scene addScene = new Scene(addParent);
        Stage addStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addStage.setScene(addScene);
        addStage.show();
    }

    /**
     *
     * @param event
     */
    @FXML
    void removeButton(ActionEvent event)
    {

    }

    /**
     * This method is for Switching between Stages.
     * @param event gets the Scene, sets the Scene, then shows it on the Stage.
     */
    @FXML
    void updateButton(ActionEvent event) throws IOException
    {
        Parent updateParent = FXMLLoader.load(getClass().getResource("/View/ScheduleUpdateView.fxml"));
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
