package AppointmentSystem.View_Controllers;

import AppointmentSystem.DAOImp.*;
import AppointmentSystem.Model.Appointments;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controller for the ReportsView.fxml, used to implement action methods and initialize values for the Stage. Uses javafx library.
 * @author josealvarezpulido
 */
public class ReportsController implements Initializable {
    /**
     * a resource bundle that gets the default Locale and the location of the resource bundle used for translation purposes.
     */
    ResourceBundle bundle = ResourceBundle.getBundle("AppointmentSystem/ResourceBundle/Nat", Locale.getDefault());

    @FXML
    private Button appointments;

    @FXML
    private Button contactSchedule;

    @FXML
    private Button userCreated;

    @FXML
    private Button exit;


    @FXML
    private TextArea reportField;

    @FXML
    private Label reportsLabel;

    /**
     * Initializes the default values and behaviours for the ReportsView.fxml file.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Title Label
        reportsLabel.setText(bundle.getString("Reports"));
        //Button Text
        appointments.setText(bundle.getString("Appointments"));
        contactSchedule.setText(bundle.getString("contactSchedule"));
        userCreated.setText(bundle.getString("User"));
        exit.setText(bundle.getString("Exit"));

    }

    @FXML
    void appointmentsButton(ActionEvent event) {
        reportField.setText("");
        int monthsInYear = 12;
        int [][] typeAndMoth = new int[monthsInYear][TypesImp.getAllTypes().size()];
        for(Appointments appointments: AppointmentImp.getAllAppointments()){
            typeAndMoth[appointments.getStart().getMonth().getValue()][appointments.getType().getId()] ++;
        }
        for(int i = 0; i < monthsInYear; i++){
            for(int j = 0; j < TypesImp.getAllTypes().size(); j++)
            {
                if(typeAndMoth[i][j] > 0){
                    reportField.appendText(typeAndMoth[i][j]+":\t"+TypesImp.getAllTypes().get(j).getTypeName()+" "+bundle.getString("For")+" "+Month.of(i)+"\n");
                }
            }
        }
    }


    @FXML
    void contactScheduleButton(ActionEvent event) {
        reportField.setText("");
        for(Contacts contacts: ContactsImp.getAllContacts()){
            for(Appointments appointments: AppointmentImp.getAllAppointments()){
                if(appointments.getContactId() == contacts.getContactId()){
                    reportField.appendText("Contact:\t"+contacts.getContactName()+ "\n"
                            +"\t"+bundle.getString("AppointmentId")+":\t"+appointments.getAppointmentId()+"\n"
                            +"\t"+bundle.getString("Title")+":\t\t\t\t"+appointments.getTitle()+"\n"
                            +"\t"+bundle.getString("Type")+":\t\t\t\t"+appointments.getType().getTypeName()+"\n"
                            +"\t"+bundle.getString("Description")+":\t\t"+appointments.getDescription()+"\n"
                            +"\t"+bundle.getString("Start")+" "+bundle.getString("DateAndTime")+":\t"+ DateTimeFormatter.ofPattern("MM/dd/yyyy - hh:mma z").format(appointments.getStart())+"\n"
                            +"\t"+bundle.getString("End")+" "+bundle.getString("DateAndTime")+":\t\t"+ DateTimeFormatter.ofPattern("MM/dd/yyyy - hh:mma z").format(appointments.getEnd())+"\n"
                            +"\t"+bundle.getString("CustomerId")+":\t\t"
                            + appointments.getCustomerId()+"\n"
                            +"\n\n");
                }
            }
        }
    }

    @FXML
    void userCreatedButton(ActionEvent event) {
        reportField.setText("");
        for(Users users: UsersImp.getAllUsers()){
            for(Customers customers: CustomersImp.getAllCustomers()){
                if(customers.getCreatedBy().equals(users.getUserName())){
                    reportField.appendText("\t"+ users.getUserName() +" "+bundle.getString("CreatedCustomer")+":\t" + customers.getCustomerName()+"\n");
                }
            }
        }
    }
    /**
     * This method is used to navigate back to the MainMenuView.fxml, setting a new stage and showing it.
     * @param event on User button ActionEvent
     * @throws IOException this is an exception handling technique that throws IOExceptions.
     */
    @FXML
    void exitButton(ActionEvent event) throws IOException {
        Parent exitParent = FXMLLoader.load(getClass().getResource("/AppointmentSystem/View_Controllers/MainMenuView.fxml"));
        Scene exitScene = new Scene(exitParent);
        Stage exitStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        exitStage.setScene(exitScene);
        exitStage.show();
    }
}
