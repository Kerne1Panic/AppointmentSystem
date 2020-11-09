package AppointmentSystem.View_Controllers;

import AppointmentSystem.DAOImp.AppointmentImp;
import AppointmentSystem.Model.Appointments;
import AppointmentSystem.Model.Customers;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller for the ScheduleMenuView.fxml, used to implement action methods and initialize values for the stage. Uses javafx library.
 * @author josealvarezpulido
 */
public class ScheduleMenuController implements Initializable {
    /**
     * a resource bundle that gets the default Locale and the location of the resource bundle used for translation purposes.
     */
    ResourceBundle bundle = ResourceBundle.getBundle("AppointmentSystem/ResourceBundle/Nat", Locale.getDefault());
    /**
     * Appointment menu Table View.
     */
    @FXML
    private TableView<Appointments> appointmentTable;
    /**
     * Appointment menu contact column.
     */
    @FXML
    private TableColumn<?, ?> contactCol;
    /**
     * Appointment menu appointment ID column.
     */
    @FXML
    private TableColumn<?, ?> appointmentIdCol;
    /**
     * Appointment menu title column.
     */
    @FXML
    private TableColumn<?, ?> titleCol;
    /**
     * Appointment menu title column.
     */
    @FXML
    private TableColumn<?, ?> descriptionCol;
    /**
     * Appointment menu customer ID column.
     */
    @FXML
    private TableColumn<Appointments, Customers> customerIDCol;
    /**
     * Appointment menu type column.
     */
    @FXML
    private TableColumn<?, ?> typeCol;
    /**
     * Appointment menu start column.
     */
    @FXML
    private TableColumn<?, ?> startCol;
    /**
     * Appointment menu end column.
     */
    @FXML
    private TableColumn<?, ?> endCol;
    /**
     * Appointment menu location column.
     */
    @FXML
    private TableColumn<?, ?> locationCol;
    /**
     * Appointment menu title label.
     */
    @FXML
    private Label titleLabel;
    /**
     * Appointment menu add button.
     */
    @FXML
    private Button addButtonText;
    /**
     * Appointment menu update button.
     */
    @FXML
    private Button updateButtonText;
    /**
     * Appointment menu remove button.
     */
    @FXML
    private Button removeButtonText;
    /**
     * Appointment menu cancel button.
     */
    @FXML
    private Button cancelButtonText;
    /**
     * Appointment menu no filter radioButton.
     */
    @FXML
    private RadioButton noFilterRadio;
    /**
     * Appointment menu weekly radioButton.
     */
    @FXML
    private RadioButton weeklyRadio;
    /**
     * Appointment menu monthly radioButton.
     */
    @FXML
    private RadioButton monthlyRadio;
    /**
     * Appointment menu error label.
     */
    @FXML
    private Label errorLabel;
    /**
     * Appointment menu radioButton filter group.
     */
    public ToggleGroup filterGroup;

    /**
     * Initializes the default values and behaviours for the ScheduleMenuView.fxml file, uses resource bundle to initialize labels, TableColumns,
     * buttons so they are in the appropriate language.
     * sets Cell Property value and sets the Items in the TableView.
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
        customerIDCol.setText(bundle.getString("CustomerName"));
        typeCol.setText(bundle.getString("Type"));
        startCol.setText(bundle.getString("Start"));
        endCol.setText(bundle.getString("End"));
        locationCol.setText(bundle.getString("Location"));
        //column property values
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        //Toggle group and radio buttons
        filterGroup = new ToggleGroup();
        this.noFilterRadio.setToggleGroup(filterGroup);
        this.monthlyRadio.setToggleGroup(filterGroup);
        this.weeklyRadio.setToggleGroup(filterGroup);
        noFilterRadio.setSelected(true);
        //set Schedule Table
        appointmentTable.setItems(AppointmentImp.getAllAppointments());
        //error label
        errorLabel.setText("");

    }

    /**
     * Controls the noFilter radio button which resets the TableView with no filters(all appointments).
     */
    @FXML
    void noFilterRadio() {

        errorLabel.setText("");
        appointmentTable.setItems(AppointmentImp.getAllAppointments());
    }

    /**
     * Controls the monthly radio Button, creates a filtered list of appointments set to the current month using ChronoUnit Months the list is then set to the appointments tableView.
     */
    @FXML
    void monthlyRadio(){
        errorLabel.setText("");
        ObservableList<Appointments> allAppointments = AppointmentImp.getAllAppointments();
        ObservableList<Appointments> filteredAppointments = FXCollections.observableArrayList();
        for(Appointments appointments : allAppointments){
           if(ChronoUnit.MONTHS.between(ZonedDateTime.now(), TimeUtil.convertBack(appointments.getStart()))< 1 && ChronoUnit.MONTHS.between(ZonedDateTime.now(), TimeUtil.convertBack(appointments.getStart())) >= 0){
               filteredAppointments.add(appointments);
           }
       }
       appointmentTable.setItems(filteredAppointments);
    }

    /**
     * Controls the weekly radio Button, creates a filtered list of appointments set to the current week using ChronoUnit Weeks the list is then set to the appointments tableView.
     */
    @FXML
    void weeklyRadio(){
        errorLabel.setText("");
        ObservableList<Appointments> allAppointments = AppointmentImp.getAllAppointments();
        ObservableList<Appointments> filteredAppointments = FXCollections.observableArrayList();
        for(Appointments appointments : allAppointments){
            if(ChronoUnit.WEEKS.between(ZonedDateTime.now(), TimeUtil.convertBack(appointments.getStart()))< 1 && ChronoUnit.WEEKS.between(ZonedDateTime.now(), TimeUtil.convertBack(appointments.getStart())) >= 0){
                filteredAppointments.add(appointments);
            }
        }
        appointmentTable.setItems(filteredAppointments);
    }

    /**
     * This method is used as an action event for the add button, changing the Scene of the Stage.
     * @param event ActionEvent
     * @throws IOException this is an exception handling technique that throws IOExceptions.
     */
    @FXML
    void addButton(ActionEvent event) throws IOException
    {
        Parent addParent = FXMLLoader.load(getClass().getResource("/AppointmentSystem/View_Controllers/ScheduleAddView.fxml"));
        Scene addScene = new Scene(addParent);
        Stage addStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addStage.setScene(addScene);
        addStage.show();
    }

    /**
     * This method removes Appointments from the database.
     * Conformation message is  displayed to make sure user  intends to delete.
     * @param event ActionEvent
     */
    @FXML
    void removeButton(ActionEvent event)
    {
        errorLabel.setText("");
        if(appointmentTable.getSelectionModel().getSelectedItem() != null){
            Appointments removeAppointment = appointmentTable.getSelectionModel().getSelectedItem();
            Alert deleteAppointment = new Alert(Alert.AlertType.CONFIRMATION, bundle.getString("CancelAppointment")+ removeAppointment.getAppointmentId());
            Optional<ButtonType> result = deleteAppointment.showAndWait();
            if(result.get() == ButtonType.OK){
                AppointmentImp.deleteAppointments(removeAppointment.getAppointmentId());
                appointmentTable.setItems(AppointmentImp.getAllAppointments());
            }
        }
        else errorLabel.setText(bundle.getString("NothingSelected"));
    }

    /**
     * This method is used as an action event for the update appointment button, changing the Scene of the Stage.
     * Appointment is sent to ScheduleUpdateView.fxml using loader to load resources and  used the controller to send over the selected Item using a method from the ScheduleAppointmentController.
     * @param event ActionEvent
     * @throws IOException this is an exception handling technique that throws IOExceptions.
     */
    @FXML
    void updateButton(ActionEvent event) throws IOException
    {
        errorLabel.setText("");
        if(appointmentTable.getSelectionModel().getSelectedItem() != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/AppointmentSystem/View_Controllers/ScheduleUpdateView.fxml"));
            loader.load();
            ScheduleUpdateController controller = loader.getController();
            Appointments appointmentsSent = appointmentTable.getSelectionModel().getSelectedItem();
            controller.appointmentSent(appointmentsSent);
            Parent update = loader.getRoot();
            Stage updateStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene updateScene = new Scene(update);
            updateStage.setScene(updateScene);
            updateStage.show();
        }
        else {
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
