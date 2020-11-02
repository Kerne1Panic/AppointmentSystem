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
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author josealvarezpulido
 */
public class ScheduleMenuController implements Initializable {
    //Resource Bundle used for changing languages
    ResourceBundle bundle = ResourceBundle.getBundle("AppointmentSystem/ResourceBundle/Nat", Locale.getDefault());

    @FXML
    private TableView<Appointments> appointmentTable;

    @FXML
    private TableColumn<?, ?> contactCol;

    @FXML
    private TableColumn<?, ?> appointmentIdCol;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private TableColumn<?, ?> descriptionCol;

    @FXML
    private TableColumn<Appointments, Customers> customerIDCol;

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

    public ToggleGroup filterGroup;

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
        //set Schedule Table
        appointmentTable.setItems(AppointmentImp.getAllAppointments());

    }

    /**
     *
     * @param event
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
     *
     * @param event
     */
    @FXML
    void removeButton(ActionEvent event)
    {
        if(appointmentTable.getSelectionModel().getSelectedItem() != null){
            Appointments removeAppointment = appointmentTable.getSelectionModel().getSelectedItem();
            AppointmentImp.deleteAppointments(removeAppointment.getAppointmentId());
            appointmentTable.setItems(AppointmentImp.getAllAppointments());
        }
        else System.out.println("No Appointment Selected.");
    }

    /**
     * This method is for Switching between Stages.
     * @param event gets the Scene, sets the Scene, then shows it on the Stage.
     */
    @FXML
    void updateButton(ActionEvent event) throws IOException
    {
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
            System.out.println("No Appointment Selected. ");
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
