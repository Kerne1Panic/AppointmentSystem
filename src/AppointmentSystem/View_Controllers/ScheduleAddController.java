package AppointmentSystem.View_Controllers;

import AppointmentSystem.DAOImp.*;
import AppointmentSystem.Model.*;
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
import java.util.*;
import java.util.stream.Collectors;

/**
 * Controller for the ScheduleAddView.fxml, used to implement action methods and initialize values for the stage. Uses javafx library.
 * @author josealvarezpulido
 */
public class ScheduleAddController implements Initializable {
    /**
     * a resource bundle that gets the default Locale and the location of the resource bundle used for translation purposes.
     */
    ResourceBundle bundle = ResourceBundle.getBundle("AppointmentSystem/ResourceBundle/Nat", Locale.getDefault());
    /**
     * appointment add title of View form label.
     */
    @FXML
    private Label titleViewLabel;
    /**
     * appointment add title label.
     */
    @FXML
    private Label titleLabel;
    /**
     * appointment add description label.
     */
    @FXML
    private Label descriptionLabel;
    /**
     * appointment add contact label.
     */
    @FXML
    private Label contactLabel;
    /**
     * appointment add location label.
     */
    @FXML
    private Label locationLabel;
    /**
     * appointment add type label.
     */
    @FXML
    private Label typeLabel;
    /**
     * appointment add contacts comboBox.
     */
    @FXML
    private ComboBox<Contacts> contactCombo;
    /**
     * appointment add type comboBox.
     */
    @FXML
    private ComboBox<Types> typeCombo;
    /**
     * appointment add start label.
     */
    @FXML
    private Label startLabel;
    /**
     * appointment add start comboBox.
     */
    @FXML
    private ComboBox<LocalTime> startCombo;
    /**
     * appointment add start datePicker.
     */
    @FXML
    private DatePicker startDate;
    /**
     * appointment add end label.
     */
    @FXML
    private Label endLabel;
    /**
     * appointment add end comboBox.
     */
    @FXML
    private ComboBox<LocalTime> endCombo;
    /**
     * appointment add end datePicker.
     */
    @FXML
    private DatePicker endDate;
    /**
     * appointment add title field.
     */
    @FXML
    private TextField titleField;
    /**
     * appointment add description field.
     */
    @FXML
    private TextField descriptionField;
    /**
     * appointment add location field.
     */
    @FXML
    private TextField locationField;
    /**
     * appointment add customer ID label.
     */
    @FXML
    private Label customerIDLabel;
    /**
     * appointment add customer ID comboBox.
     */
    @FXML
    private ComboBox<Customers> customerIDCombo;
    /**
     * appointment add user label.
     */
    @FXML
    private Label userLabel;
    /**
     * appointment add user comboBox.
     */
    @FXML
    private ComboBox<Users> userCombo;
    /**
     * appointment add save button.
     */
    @FXML
    private Button saveButtonText;
    /**
     * appointment add cancel button.
     */
    @FXML
    private Button cancelButtonText;
    /**
     * appointment add error label.
     */
    @FXML
    private Label errorLabel;
    /**
     * Observable list of hours the Offices are open (8:00 am - 10:00pm EST), the list will contain
     * these times in increments of 15 minutes in the detected local time.
     */
    ObservableList<LocalTime> hoursOpen = FXCollections.observableArrayList();
    /**
     * The opening time of the Offices(8:00am EST).
     */
    LocalTime startTimes;
    /**
     * The closing time of the Offices(10:00pm EST).
     */
    LocalTime endTimes;
    /**
     * The amount each value will be incremented in the list.
     */
    int incrementMin;
    /**
     * Appointment add max length of an appointment.
     */
    int maxLength;

    /**
     * initialize the buttons and labels in this method to use the resource bundle to change language,
     * as well as comboBoxes and start/end times.
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
        typeCombo.setItems(TypesImp.getAllTypes());
        //Initialize ComboTime box
        //Business hours are dedicated in Eastern Time.
        startTimes = TimeUtil.convertBack(ZonedDateTime.of(LocalDateTime.of(LocalDate.now(),LocalTime.of(8,0)),ZoneId.of("America/New_York"))).toLocalTime();
        endTimes = TimeUtil.convertBack(ZonedDateTime.of(LocalDateTime.of(LocalDate.now(),LocalTime.of(22, 0)),ZoneId.of("America/New_York"))).toLocalTime();
        incrementMin = 15;
        maxLength = 2;
        hoursOpen = TimeUtil.getTimes(startTimes,endTimes,incrementMin);
        startCombo.setItems(hoursOpen);
        //errorLabel
        errorLabel.setText("");

    }

    /**
     * action method ,on action this method creates a filtered list in local time for the end of the appointment, the list is dependent on the chosen start time.
     * The maximum length of an appointment is set to 2 hours, could be changed using maxLength variable.
     * assert exception handling a possible null value.
     */
    public void startTimeCombo()
    {
        errorLabel.setText("");
        if(startCombo.getValue() != null){
            ObservableList<LocalTime> endAppointment = TimeUtil.getTimes(startCombo.getValue(),startCombo.getValue().plusHours(maxLength).plusMinutes(incrementMin),incrementMin);
            ObservableList<LocalTime> filterEndAppointment = FXCollections.observableArrayList();
            //exception handling.
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
     * action method for the start date picker, on action this method sets the end date picker.
     * This is assuming that appointments must end the same day they are started.
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
     * On action this method sets local variables to the values inputted to the form saving them as appointments in the database
     * then goes through try catch exception handling as well as if statement to handle possible null values.
     * Stream used to create a idMatch list.
     * Stream used to determine appointment overlap using anyMatch.
     * lambda discussion, Lambda in boolean appointment overlap is used as a predicate
     * to return a boolean, a lambda is necessary with streams using functional programing methods.
     * @param event ActionEvent
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
                Types type = typeCombo.getValue();
                ZoneId myZoneId = ZoneId.systemDefault();
                LocalTime startTime = startCombo.getValue();
                LocalDate dateStart = startDate.getValue();
                LocalTime endTime = endCombo.getValue();
                LocalDate dateEnd = endDate.getValue();
                LocalDateTime  createDateTime = LocalDateTime.now();
                LocalDateTime startAppointment = TimeUtil.convertToUTC(LocalDateTime.of(dateStart,startTime),myZoneId);
                LocalDateTime endAppointment = TimeUtil.convertToUTC(LocalDateTime.of(dateEnd,endTime), myZoneId);
                LocalDateTime create = TimeUtil.convertToUTC(createDateTime,myZoneId);
                String createdBy = UsersImp.getUserLoggedIn();
                int customerId = customerIDCombo.getSelectionModel().getSelectedItem().getCustomerId();
                int userId = userCombo.getSelectionModel().getSelectedItem().getUserId();
                int contactId = contactCombo.getSelectionModel().getSelectedItem().getContactId();

                ZonedDateTime newStart = TimeUtil.convertBack(ZonedDateTime.of(startAppointment,ZoneId.of("UTC")));
                ZonedDateTime newEnd = TimeUtil.convertBack(ZonedDateTime.of(endAppointment,ZoneId.of("UTC")));
                //Stream used to create a List of idMatched for a customer's appointments
                ObservableList<Appointments> allAppointments = AppointmentImp.getAllAppointments();
                List<Appointments> idMatchAppointments =allAppointments.stream()
                        .filter(appointments -> appointments.getCustomerId() == customerId)
                        .collect(Collectors.toList());
                //Stream used to find if appointments are overlapped for a customer based on ID.
                boolean appointmentOverlap = idMatchAppointments.stream()
                        .anyMatch(old ->
                        {
                            return  ((old.getStart().isBefore(newStart)||old.getStart().isEqual(newStart))   &&  (old.getEnd().isAfter(newStart) || old.getEnd().isEqual(newStart)))
                                    ||
                                    ((old.getStart().isBefore(newEnd) || old.getStart().isEqual(newEnd))    &&  (old.getEnd().isAfter(newEnd) || old.getEnd().isEqual(newEnd)))
                                    ||
                                    ((old.getStart().isAfter(newStart) || old.getStart().isEqual(newStart)) &&  (old.getEnd().isBefore(newEnd) || old.getEnd().isEqual(newEnd)));
                        });
                if(appointmentOverlap){
                    //Alert conflicting appointments.
                    Alert overlap = new Alert(Alert.AlertType.INFORMATION, bundle.getString("Overlap"));
                    overlap.showAndWait();
                }
                else {
                    AppointmentImp.addAppointments(title,description,location,type.getTypeName(),startAppointment,endAppointment,create,createdBy,customerId,userId,contactId);
                    Parent cancelParent = FXMLLoader.load(getClass().getResource("/AppointmentSystem/View_Controllers/ScheduleMenuView.fxml"));
                    Scene cancelScene = new Scene(cancelParent);
                    Stage cancelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
