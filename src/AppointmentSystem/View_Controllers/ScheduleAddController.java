package AppointmentSystem.View_Controllers;

import AppointmentSystem.DAOImp.AppointmentImp;
import AppointmentSystem.DAOImp.ContactsImp;
import AppointmentSystem.DAOImp.CustomersImp;
import AppointmentSystem.DAOImp.UsersImp;
import AppointmentSystem.Model.Contacts;
import AppointmentSystem.Model.Customers;
import AppointmentSystem.Model.Users;
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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author josealvarezpulido
 */
public class ScheduleAddController implements Initializable {
    //Resource Bundle used for changing languages
    ResourceBundle bundle = ResourceBundle.getBundle("AppointmentSystem/ResourceBundle/Nat", Locale.getDefault());

    @FXML
    private Label titleViewLabel;

    @FXML
    private Label titleLabel;

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

    @FXML
    private Label errorLabel;


    ObservableList<LocalTime> hoursOpen = FXCollections.observableArrayList();
    LocalTime startTimes;
    LocalTime endTimes;
    int incrementMin;

    /**
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Title View Name
        titleViewLabel.setText(bundle.getString("AddAppointment"));
        //Label Text
        titleLabel.setText(bundle.getString("Title"));
        descriptionLabel.setText(bundle.getString("Description"));
        locationLabel.setText(bundle.getString("Location"));
        contactLabel.setText(bundle.getString("Contact"));
        typeLabel.setText(bundle.getString("Type"));
        startLabel.setText(bundle.getString("Start"));
        endLabel.setText(bundle.getString("End"));
        customerIDLabel.setText(bundle.getString("Customer"));
        userLabel.setText(bundle.getString("User"));
        //Button Text
        saveButtonText.setText(bundle.getString("Save"));
        cancelButtonText.setText(bundle.getString("Cancel"));
        //Initialize ComboBoxes
        contactCombo.setItems(ContactsImp.getAllContacts());
        customerIDCombo.setItems(CustomersImp.getAllCustomers());
        userCombo.setItems(UsersImp.getAllUsers());
        typeCombo.getItems().addAll("De-Briefing","Planning Session","Meeting","One-on-One","Training","On Boarding");
        //Initialize ComboTime box
        //Business hours are dedicated in Eastern Time.
        startTimes = TimeUtil.convertBack(ZonedDateTime.of(LocalDateTime.of(LocalDate.now(),LocalTime.of(8,0)),ZoneId.of("America/New_York"))).toLocalTime();
        endTimes = TimeUtil.convertBack(ZonedDateTime.of(LocalDateTime.of(LocalDate.now(),LocalTime.of(22, 0)),ZoneId.of("America/New_York"))).toLocalTime();
        incrementMin = 15;
        hoursOpen = TimeUtil.getTimes(startTimes,endTimes,incrementMin);
        startCombo.setItems(hoursOpen);
        endCombo.setPromptText("Select Start hours");
        //errorLabel
        errorLabel.setText("");

    }

    /**
     *
     */
    public void startTimeCombo()
    {
        errorLabel.setText("");
        if(startCombo.getValue() != null){
            ObservableList<LocalTime> endAppointment = TimeUtil.getTimes(startCombo.getValue(),startCombo.getValue().plusHours(2).plusMinutes(incrementMin),incrementMin);
            ObservableList<LocalTime> filterEndAppointment = FXCollections.observableArrayList();
            /**
             * assert Exception handling.
             */
            assert endAppointment != null;
            for(LocalTime time : endAppointment){
                if(time.isBefore(endTimes.plusMinutes(incrementMin)) && time.isAfter(startCombo.getValue())){
                    filterEndAppointment.add(time);
                }
            }
            endCombo.setItems(filterEndAppointment);
        }
    }

    /**
     *
     */
    public void startDatePicker()
    {
        errorLabel.setText("");
        if(startDate.getValue() != null){
            endDate.setValue(startDate.getValue());
        }
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
        errorLabel.setText("");
        String title = titleField.getText();
        String description = descriptionField.getText();
        String location = locationField.getText();
        try{
            if(!titleField.getText().isBlank() &&typeCombo.getValue() != null && !descriptionField.getText().isBlank() && !locationField.getText().isBlank()
            && startCombo.getValue() != null && startDate.getValue() != null && endCombo.getValue() != null && customerIDCombo.getValue() != null
            && userCombo.getValue() != null && contactCombo.getValue() != null)
            {
                String type = typeCombo.getValue();
                ZoneId myZoneId = ZoneId.systemDefault();
                LocalTime startTime = startCombo.getValue();
                LocalDate dateStart = startDate.getValue();
                LocalTime endTime = endCombo.getValue();
                LocalDate dateEnd = endDate.getValue();
                LocalDateTime  createDateTime = LocalDateTime.now();
                LocalDateTime start = TimeUtil.convertToUTC(LocalDateTime.of(dateStart,startTime),myZoneId);
                LocalDateTime end = TimeUtil.convertToUTC(LocalDateTime.of(dateEnd,endTime), myZoneId);
                LocalDateTime create = TimeUtil.convertToUTC(createDateTime,myZoneId);
                String createdBy = UsersImp.getUserLoggedIn();
                int customerId = customerIDCombo.getSelectionModel().getSelectedItem().getCustomerId();
                int userId = userCombo.getSelectionModel().getSelectedItem().getUserId();
                int contactId = contactCombo.getSelectionModel().getSelectedItem().getContactId();

                AppointmentImp.addAppointments(title,description,location,type,start,end,create,createdBy,customerId,userId,contactId);
                Parent cancelParent = FXMLLoader.load(getClass().getResource("/AppointmentSystem/View_Controllers/ScheduleMenuView.fxml"));
                Scene cancelScene = new Scene(cancelParent);
                Stage cancelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                cancelStage.setScene(cancelScene);
                cancelStage.show();
            }
            else {
                errorLabel.setText(bundle.getString("MissingValues"));
            }
        }catch (Exception e){
            errorLabel.setText(e.getLocalizedMessage());
        }
    }
}
