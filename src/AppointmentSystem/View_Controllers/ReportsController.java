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
    /**
     * Reports appointments button.
     */
    @FXML
    private Button appointments;
    /**
     * Reports contact Schedule button.
     */
    @FXML
    private Button contactSchedule;
    /**
     * Reports user's created by button.
     */
    @FXML
    private Button userCreated;
    /**
     * Reports exit button.
     */
    @FXML
    private Button exit;
    /**
     * Reports report field textArea.
     */
    @FXML
    private TextArea reportField;
    /**
     * Reports title label.
     */
    @FXML
    private Label reportsLabel;

    /**
     * Initializes the default values and behaviours for the ReportsView.fxml file uses resource bundle to set the language.
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

    /**
     * On action this method appends text (count type and month( to the reportField using a 2D array to store count of type of appointments by month,
     * the 2D array typeAndMonth goes through 2 for loops to using the Type Id as an index for Type and month.getValue()-1 as index for month.
     */
    @FXML
    void appointmentsButton() {
        reportField.setText("Appointment count by Type and Moth:\n");
        int monthsInYear = 12;
        int [][] typeAndMoth = new int[monthsInYear][TypesImp.getAllTypes().size()];
        for(Appointments appointments: AppointmentImp.getAllAppointments()){
            //since month values start with index 1, must subtract 1 to index properly in 2D array.
            typeAndMoth[appointments.getStart().getMonth().getValue()-1][appointments.getType().getId()] ++;
        }
        for(int i = 0; i < monthsInYear; i++){
            for(int j = 0; j < TypesImp.getAllTypes().size(); j++)
            {
                if(typeAndMoth[i][j] > 0){
                    //since arrays start index at 0 to get the proper month must add 1 to the index i when getting month value.
                    reportField.appendText(typeAndMoth[i][j]+":\t"+TypesImp.getAllTypes().get(j).getTypeName()+" "+bundle.getString("For")+" "+Month.of(i+1)+"\n");
                }
            }
        }
    }

    /**
     * On action this method appends text to reportField a schedule for contacts including appointmentID, title, Type, Description, Start, End, CustomerID.
     * Uses for loop to cycle through contacts then through appointments.
     */
    @FXML
    void contactScheduleButton() {
        reportField.setText("Schedule for Contacts:\n");
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

    /**
     * On action this method appends text to reportField of the Users and the Customers they have created, UserCreated is defined by the customer that is logged in.
     * Uses a for loop to cycle through Users and then a for loop to cycle through Customers to get a match.
     */
    @FXML
    void userCreatedButton() {
        reportField.setText("Users Created Customers:\n");
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
