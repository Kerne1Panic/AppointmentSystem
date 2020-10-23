package AppointmentSystem.View_Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author josealvarezpulido
 */
public class MainMenuController implements Initializable {
    //Resource Bundle used for changing languages
    ResourceBundle bundle = ResourceBundle.getBundle("AppointmentSystem/ResourceBundle/Nat", Locale.getDefault());

    @FXML
    private Button customerButton;

    @FXML
    private Button scheduleButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label titleLabel;

    /**
     *
     * @param url
     * @param resourceBundle
     * initialize the buttons and labels in this method to use the resource bundle to change language.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        titleLabel.setText(bundle.getString("MainMenu"));
        customerButton.setText(bundle.getString("Customer"));
        scheduleButton.setText(bundle.getString("Schedule"));
        exitButton.setText(bundle.getString("Exit"));

    }

    @FXML
    void customerMenuButton(ActionEvent event) throws IOException
    {
        Parent customerMenuParent = FXMLLoader.load(getClass().getResource("/AppointmentSystem/View_Controllers/CustomerMenuView.fxml"));
        Scene customerMenuScene = new Scene(customerMenuParent);
        Stage customerMenuStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        customerMenuStage.setScene(customerMenuScene);
        customerMenuStage.show();
    }
    @FXML
    void scheduleMenuButton(ActionEvent event)  throws IOException
    {
        Parent scheduleMenuParent = FXMLLoader.load(getClass().getResource("/AppointmentSystem/View_Controllers/ScheduleMenuView.fxml"));
        Scene scheduleMenuScene = new Scene(scheduleMenuParent);
        Stage scheduleMenuStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scheduleMenuStage.setScene(scheduleMenuScene);
        scheduleMenuStage.show();
    }
    @FXML
    void exitButton(ActionEvent event) throws IOException
    {
        Parent exitParent = FXMLLoader.load(getClass().getResource("/AppointmentSystem/View_Controllers/LogInView.fxml"));
        Scene exitScene = new Scene(exitParent);
        Stage exitStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        exitStage.setScene(exitScene);
        exitStage.show();
    }
}
