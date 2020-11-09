package AppointmentSystem.View_Controllers;

import AppointmentSystem.DAOImp.*;
import AppointmentSystem.LambdaInterfaces.LambdaContacts;
import AppointmentSystem.LambdaInterfaces.LambdaCustomers;
import AppointmentSystem.LambdaInterfaces.LambdaUsers;
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
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Controller for the ScheduleUpdateView.fxml, used to implement action methods and initialize values for the stage. Uses javafx library.
 * @author josealvarezpulido
 */
public class ScheduleUpdateController implements Initializable {
    /**
     * a resource bundle that gets the default Locale and the location of the resource bundle used for translation purposes.
     */
    ResourceBundle bundle = ResourceBundle.getBundle("AppointmentSystem/ResourceBundle/Nat", Locale.getDefault());
    /**
     * Appointment Update title View form label.
     */
    @FXML
    private Label titleViewLabel;
    /**
     * Appointment Update title label.
     */
    @FXML
    private Label titleLabel;
    /**
     * Appointment Update appointment ID label.
     */
    @FXML
    private Label appointmentIDLabel;
    /**
     * Appointment Update appointment ID text label.
     */
    @FXML
    private Label appointmentIDText;
    /**
     * Appointment Update description label.
     */
    @FXML
    private Label descriptionLabel;
    /**
     * Appointment Update contact label.
     */
    @FXML
    private Label contactLabel;
    /**
     * Appointment Update location label.
     */
    @FXML
    private Label locationLabel;
    /**
     * Appointment Update type label.
     */
    @FXML
    private Label typeLabel;
    /**
     * Appointment Update contacts comboBox.
     */
    @FXML
    private ComboBox<Contacts> contactCombo;
    /**
     * Appointment Update types comboBox.
     */
    @FXML
    private ComboBox<Types> typeCombo;
    /**
     * Appointment Update start label.
     */
    @FXML
    private Label startLabel;
    /**
     * Appointment Update start comboBox.
     */
    @FXML
    private ComboBox<LocalTime> startCombo;
    /**
     * Appointment Update start datePicker.
     */
    @FXML
    private DatePicker startDate;
    /**
     * Appointment Update end label.
     */
    @FXML
    private Label endLabel;
    /**
     * Appointment Update end comboBox.
     */
    @FXML
    private ComboBox<LocalTime> endCombo;
    /**
     * Appointment Update end datePicker.
     */
    @FXML
    private DatePicker endDate;
    /**
     * Appointment Update title field.
     */
    @FXML
    private TextField titleField;
    /**
     * Appointment Update description field.
     */
    @FXML
    private TextField descriptionField;
    /**
     * Appointment Update location field.
     */
    @FXML
    private TextField locationField;
    /**
     * Appointment Update customer ID label.
     */
    @FXML
    private Label customerIDLabel;
    /**
     * Appointment Update customer comboBox.
     */
    @FXML
    private ComboBox<Customers> customerIDCombo;
    /**
     * Appointment Update user label.
     */
    @FXML
    private Label userLabel;
    /**
     * Appointment Update users comboBox.
     */
    @FXML
    private ComboBox<Users> userCombo;
    /**
     * Appointment Update save button.
     */
    @FXML
    private Button saveButtonText;
    /**
     * Appointment Update cancel button.
     */
    @FXML
    private Button cancelButtonText;
    /**
     * Appointment Update error label.
     */
    @FXML
    private Label errorLabel;
    /**
     * Appointment Update Appointment ID.
     */
    private int appointmentID;
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
     * Appointment Update max length of an appointment.
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
        //Initialize ComboBoxes
        contactCombo.setItems(ContactsImp.getAllContacts());
        customerIDCombo.setItems(CustomersImp.getAllCustomers());
        userCombo.setItems(UsersImp.getAllUsers());
        typeCombo.setItems(TypesImp.getAllTypes());
        //Initialize ComboTime box
        startTimes = TimeUtil.convertBack(ZonedDateTime.of(LocalDateTime.of(LocalDate.now(),LocalTime.of(8,0)),ZoneId.of("America/New_York"))).toLocalTime();
        endTimes = TimeUtil.convertBack(ZonedDateTime.of(LocalDateTime.of(LocalDate.now(),LocalTime.of(22, 0)),ZoneId.of("America/New_York"))).toLocalTime();
        incrementMin = 15;
        maxLength = 2;
        hoursOpen = TimeUtil.getTimes(startTimes,endTimes,incrementMin);
        startCombo.setItems(hoursOpen);
        //error label
        errorLabel.setText("");

    }

    /**
     * Passed appointment Object from ScheduleMenuController sets the values of the form.
     * lambda discussion 1, LambdaCustomers is used to return a Customer Object which is needed to set the Customer comboBox.
     * lambda discussion 2, LambdaUsers is used to return a User Object which is needed to set the Users comboBox
     * lambda discussion 3, LambdaContacts is used to return a Contact Object which is needed to set the Contact comboBox
     * assert Exception handling.
     * @param appointments the appointment that is being passed selected from appointment table view.
     */
    public void appointmentSent(Appointments appointments)
    {
        setAppointmentID(appointments.getAppointmentId());
        appointmentIDText.setText(String.valueOf(appointments.getAppointmentId()));
        titleField.setText(appointments.getTitle());
        descriptionField.setText(appointments.getDescription());
        locationField.setText(appointments.getLocation());
        typeCombo.setValue(appointments.getType());
        startCombo.setValue(TimeUtil.convertBack(appointments.getStart()).toLocalTime());
        endCombo.setValue(TimeUtil.convertBack(appointments.getEnd()).toLocalTime());
        startDate.setValue(TimeUtil.convertBack(appointments.getStart()).toLocalDate());
        endDate.setValue(TimeUtil.convertBack(appointments.getEnd()).toLocalDate());
        //Sets the endCombo box with filtered values based on 2 hour max meeting times.
        ObservableList<LocalTime> endAppointment = TimeUtil.getTimes(startCombo.getValue(),startCombo.getValue().plusHours(2).plusMinutes(incrementMin),incrementMin);
        ObservableList<LocalTime> filterEndAppointment = FXCollections.observableArrayList();
        //exception handling.
        assert endAppointment != null;
        for(LocalTime time : endAppointment){
            if(time.isBefore(endTimes.plusMinutes(incrementMin)) && time.isAfter(startCombo.getValue())){
                filterEndAppointment.add(time);
            }
        }
        endCombo.setItems(filterEndAppointment);
        LambdaCustomers customerRetrieve = (customerID) ->{
            for(Customers customer : CustomersImp.getAllCustomers()){
                if(customer.getCustomerId() == customerID){
                    return customer;
                }
            }
            return null;
        };
        LambdaUsers userRetrieve = (userID) ->{
            for(Users user : UsersImp.getAllUsers()){
                if(user.getUserId() == userID){
                    return user;
                }
            }
            return null;
        };
        LambdaContacts contactRetrieve = (contactID) ->{
            for(Contacts contact : ContactsImp.getAllContacts()){

                if(contact.getContactId() == contactID){
                    return contact;
                }
            }
            return null;
        };
        //using the lambda values to store them in combo boxes.
        customerIDCombo.setValue(customerRetrieve.getCustomer(appointments.getCustomerId()));
        userCombo.setValue(userRetrieve.getUser(appointments.getUserId()));
        contactCombo.setValue(contactRetrieve.getContact(appointments.getContactId()));
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
            //Exception handling.
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
     * On action this method sets local variables to the values inputted to the form updating the appointment in the database
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
        int appointmentId = getAppointmentID();
        String title = titleField.getText();
        String description = descriptionField.getText();
        String location = locationField.getText();
        try{
            int contactId = contactCombo.getSelectionModel().getSelectedItem().getContactId();
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
                LocalDateTime updateTime = LocalDateTime.now();
                LocalDateTime startAppointment = TimeUtil.convertToUTC(LocalDateTime.of(dateStart,startTime),myZoneId);
                LocalDateTime endAppointment = TimeUtil.convertToUTC(LocalDateTime.of(dateEnd,endTime), myZoneId);
                LocalDateTime update = TimeUtil.convertToUTC(updateTime,myZoneId);
                String updatedBy = UsersImp.getUserLoggedIn();
                int customerId = customerIDCombo.getSelectionModel().getSelectedItem().getCustomerId();
                int userId = userCombo.getSelectionModel().getSelectedItem().getUserId();
                
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
                            return  (appointmentId != old.getAppointmentId())//appointment ID used to avoid the same appointment overlapping with itself in an update.
                                    &&
                                    (((old.getStart().isBefore(newStart)||old.getStart().isEqual(newStart))   &&  (old.getEnd().isAfter(newStart) || old.getEnd().isEqual(newStart)))
                                    ||
                                    ((old.getStart().isBefore(newEnd) || old.getStart().isEqual(newEnd))    &&  (old.getEnd().isAfter(newEnd) || old.getEnd().isEqual(newEnd)))
                                    ||
                                    ((old.getStart().isAfter(newStart) || old.getStart().isEqual(newStart)) &&  (old.getEnd().isBefore(newEnd) || old.getEnd().isEqual(newEnd))));
                        });

                if(appointmentOverlap){
                    //Alert conflicting appointments.
                    Alert overlap = new Alert(Alert.AlertType.INFORMATION, bundle.getString("Overlap"));
                    overlap.showAndWait();
                }
                else{
                    AppointmentImp.updateAppointments(title,description,location,type.getTypeName(),startAppointment,endAppointment,update,updatedBy,customerId,userId,contactId,appointmentId);
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

    /**
     * method that returns the appointment ID
     * @return appointmentID
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * method that sets the appointmentID.
     * @param appointmentID sets the appointmentID.
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }
}
