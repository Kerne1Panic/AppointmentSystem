package AppointmentSystem.View_Controllers;

import AppointmentSystem.DAOImp.AppointmentImp;
import AppointmentSystem.DAOImp.TypesImp;
import AppointmentSystem.DAOImp.UsersImp;
import AppointmentSystem.LambdaInterfaces.LambdaAppointments;
import AppointmentSystem.Model.Appointments;
import AppointmentSystem.Model.Types;
import AppointmentSystem.Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author josealvarezpulido
 * Controller for the LogInView.fxml, used to implement action methods and initialize values for the stage. Uses javafx library.
 * Uses labels, text fields, and password fileds.
 * An alert message is created if the login credentials do not match the username and password from the database.
 */
public class LogInController implements Initializable {
    /**
     * a resource bundle that gets the default Locale and the location of the resource bundle used for translation purposes.
     */
    ResourceBundle bundle = ResourceBundle.getBundle("AppointmentSystem/ResourceBundle/Nat", Locale.getDefault());
    /**
     * Login locale Message set to the ZoneId of the User.
     */
    @FXML
    private Label localeMessage;
    /**
     * Login username label.
     */
    @FXML
    private Label usernameLabel;
    /**
     * Login password label.
     */
    @FXML
    private Label passwordLabel;
    /**
     * Login title Label.
     */
    @FXML
    private Label titleLabel;
    /**
     * Login username field.
     */
    @FXML
    private TextField usernameField;
    /**
     * Login password field.
     */
    @FXML
    private PasswordField passwordField;
    /**
     * Alert used to inform User if the username or passwords don't match.
     */
    Alert alert = new Alert(Alert.AlertType.ERROR, bundle.getString("WrongCredentials"));

    /**
     * Labels are initialized using a key from the resource bundle to translate them.
     * Initializes the Types used in the App.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Types initialized for the App
        Types type1 = new Types(0,"De-Briefing");
        Types type2 = new Types(1,"Planning Session");
        Types type3 = new Types(2,"Meeting");
        Types type4 = new Types(3, "Training");
        Types type5 = new Types(4,"On Boarding");
        TypesImp.addTypes(type1);
        TypesImp.addTypes(type2);
        TypesImp.addTypes(type3);
        TypesImp.addTypes(type4);
        TypesImp.addTypes(type5);
        //login labels
        usernameLabel.setText(bundle.getString("Username")+":");
        passwordLabel.setText(bundle.getString("Password")+":");
        titleLabel.setText(bundle.getString("LoginScreen"));
        //sets locale message
        localeMessage.setText(String.valueOf(ZoneId.systemDefault()));
    }
    /**
     * The Username text is matched against the usernames in the database, if there is a match the passwords are compared.
     * A new stage is then set, changing the view to the Main menu.
     * A try catch is used to catch exceptions of wrong or null usernames typed by the user.
     * lambda discussion, LambdaAppointments used to return an a Appointment Object which is needed to compare to the current LocalDateTime to display a message if an appointment is within 15 minutes of the current LocalDateTime,
     * a lambda is perfect for a situation where you need to create and use a method within a method, it is more efficient than creating a separate method just for code that will only be use in this instance.
     * Uses FileWriter and PrintWriter to keep a record of login attempts successful or not so long as the username matches a record in the database.
     * @param event when the enter button is pressed on either text field or password field executing the method.
     * @throws SQLException exception handling.
     * @throws IOException exception handling used to prevent errors.
     */
    public void logIn(ActionEvent event) throws IOException, SQLException {
        //fileWriter and printerWriter declaration append is set to true.
        FileWriter fwLoginActivity = new FileWriter("login_activity.txt",true);
        PrintWriter pwLoginActivity = new PrintWriter(fwLoginActivity);

        ZonedDateTime userZDT = ZonedDateTime.now();
        //try catch is used to prevent crashing based on human error.
        try{
            //Users Object created using the getUser(String sql) static function from a matched user in the database.
            Users user = UsersImp.getUser(usernameField.getText());
            //not null user input check.
            if(user != null){
                //comparing the user names to the ones in the database, case sensitive.
                if(user.getPassword().equals(passwordField.getText())){
                    //print writer used to write if a login was successful into login_activity.txt.
                    pwLoginActivity.println(bundle.getString("User")+": "+user.getUserName()+":\t"+bundle.getString("LoginSuccess")+"\t"+DateTimeFormatter.ofPattern("MM/dd/yyyy - hh:mm:ssa z").format(userZDT)+"\n");
                    pwLoginActivity.close();

                    //Displays a message if an appointment is within 15 minutes for the User.
                    //Convert into user local time for comparison
                    LambdaAppointments comingUp = (currentZDT) -> {
                        for(Appointments appointments : AppointmentImp.getAllAppointments()){
                            if((appointments.getStart().minusMinutes(15).isEqual(currentZDT) || appointments.getStart().minusMinutes(15).isBefore(currentZDT)) && !appointments.getStart().isBefore(currentZDT)){
                                return appointments;
                            }
                        }
                        return null;
                    };
                    if(comingUp.getAppointments(userZDT) !=null){
                        Appointments appointments = comingUp.getAppointments(userZDT);
                        //Alert message
                        Alert appointmentSoon = new Alert(Alert.AlertType.INFORMATION, bundle.getString("AppointmentWith")+ " " +appointments.getAppointmentId()+ " " +bundle.getString("within15") + "\n" + appointments.getStart());
                        appointmentSoon.showAndWait();
                    }
                    else {
                        //Alert No appointments
                        Alert noAppointments = new Alert(Alert.AlertType.INFORMATION, bundle.getString("NoAppointments"));
                        noAppointments.showAndWait();

                    }
                    Parent mainMenuParent = FXMLLoader.load(getClass().getResource("/AppointmentSystem/View_Controllers/MainMenuView.fxml"));
                    Scene mainMenuScene = new Scene(mainMenuParent);
                    Stage mainMenu = (Stage)((Node)event.getSource()).getScene().getWindow();
                    mainMenu.setScene(mainMenuScene);
                    mainMenu.show();
                    //sets the userLoggedIn String. The String is used to determine which user is logged in.
                    UsersImp.setUserLoggedIn(user.getUserName());
                }
                else {
                    //alert displayed if no usernames are matched.
                    alert.showAndWait();
                    //print writer used to write if a login was not successful into login_activity.txt.
                    pwLoginActivity.println(bundle.getString("User")+": "+user.getUserName()+":\t"+bundle.getString("LoginFailed")+"\t"+DateTimeFormatter.ofPattern("MM/dd/yyyy - hh:mm:ssa z").format(userZDT)+"\n");
                    pwLoginActivity.close();

                }
            }
            else {
                //alert is displayed if text field is left null.
                alert.showAndWait();
            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }
}
