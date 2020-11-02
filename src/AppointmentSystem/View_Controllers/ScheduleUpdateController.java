package AppointmentSystem.View_Controllers;

import AppointmentSystem.DAOImp.UsersImp;
import AppointmentSystem.Model.Contacts;
import AppointmentSystem.Model.Customers;
import AppointmentSystem.Model.Users;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author josealvarezpulido
 */
public class ScheduleUpdateController implements Initializable {
    //Resource Bundle used for changing languages
    ResourceBundle bundle = ResourceBundle.getBundle("AppointmentSystem/ResourceBundle/Nat", Locale.getDefault());

    @FXML
    private Label titleViewLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label appointmentIDLabel;

    @FXML
    private Label appointmentIDText;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label contactLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private ComboBox<Contacts> contactCombo;

    @FXML
    private ComboBox<String> typeCombo;

    @FXML
    private Label startLabel;

    @FXML
    private ComboBox<LocalTime> startCombo;

    @FXML
    private DatePicker startDate;

    @FXML
    private Label endLabel;

    @FXML
    private ComboBox<LocalTime> endCombo;

    @FXML
    private DatePicker endDate;

    @FXML
    private TextField titleField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField locationField;

    @FXML
    private Label customerIDLabel;

    @FXML
    private ComboBox<Customers> customerIDCombo;

    @FXML
    private Label userLabel;

    @FXML
    private ComboBox<Users> userCombo;

    @FXML
    private Button saveButtonText;

    @FXML
    private Button cancelButtonText;

    /**
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Title View Name
        titleViewLabel.setText(bundle.getString("UpdateAppointment"));
        //Label Text
        appointmentIDLabel.setText(bundle.getString("AppointmentId"));
        titleLabel.setText(bundle.getString("Title"));
        descriptionLabel.setText(bundle.getString("Description"));
        locationLabel.setText(bundle.getString("Location"));
        contactLabel.setText(bundle.getString("Contact"));
        typeLabel.setText(bundle.getString("Type"));
        startLabel.setText(bundle.getString("Start"));
        endLabel.setText(bundle.getString("End"));
        customerIDLabel.setText(bundle.getString("CustomerId"));
        userLabel.setText(bundle.getString("User"));
        //Button Text
        saveButtonText.setText(bundle.getString("Save"));
        cancelButtonText.setText(bundle.getString("Cancel"));

    }

    /**
     * This method is for Switching between Stages.
     * @param event gets the Scene, sets the Scene, then shows it on the Stage.
     * @throws IOException is used for exception handling.
     */
    @FXML
    void cancelButton(ActionEvent event) throws IOException
    {
        Parent cancelParent = FXMLLoader.load(getClass().getResource("/AppointmentSystem/View_Controllers/ScheduleMenuView.fxml"));
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
    void saveButton(ActionEvent event)
    {
        String title = titleField.getText();
        String description = descriptionField.getText();
        String location = locationField.getText();
        String type = typeCombo.getValue();
        ZoneId myZoneId = ZoneId.systemDefault();
        LocalTime startTime = startCombo.getValue();
        LocalDate dateStart = startDate.getValue();
        LocalTime endTime = endCombo.getValue();
        LocalDate dateEnd = endDate.getValue();
        LocalDateTime createDateTime = LocalDateTime.now();
        String createdBy = UsersImp.getUserLoggedIn();
        int customerId = customerIDCombo.getSelectionModel().getSelectedItem().getCustomerId();
        int userId = userCombo.getSelectionModel().getSelectedItem().getUserId();
        int contactId = contactCombo.getSelectionModel().getSelectedItem().getContactId();
    }
}
