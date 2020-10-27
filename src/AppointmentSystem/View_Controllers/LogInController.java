package AppointmentSystem.View_Controllers;

import AppointmentSystem.DAOImp.UsersImp;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author josealvarezpulido
 */
public class LogInController implements Initializable {
        //Resource Bundle used for changing languages
    ResourceBundle bundle = ResourceBundle.getBundle("AppointmentSystem/ResourceBundle/Nat", Locale.getDefault());

    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField usernamePassword;

    Alert alert = new Alert(Alert.AlertType.ERROR, bundle.getString("WrongCredentials"));

    /**
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        usernameLabel.setText(bundle.getString("Username")+":");
        passwordLabel.setText(bundle.getString("Password")+":");
        titleLabel.setText(bundle.getString("LoginScreen"));
    }

    String username = "test";
    String password = "test";
    /**
     * This method is used to verify that the credentials are correct otherwise an error message is displayed.
     * A new stage is then set, changing the view to the Main menu.
     * @param event when the enter button is pressed on either text field or password field.
     */
    public void logIn(ActionEvent event) throws IOException
    {
            if(usernamePassword.getText().equals(password) && usernameText.getText().equals(username))
            {
                Parent mainMenuParent = FXMLLoader.load(getClass().getResource("/AppointmentSystem/View_Controllers/MainMenuView.fxml"));
                Scene mainMenuScene = new Scene(mainMenuParent);
                Stage mainMenu = (Stage)((Node)event.getSource()).getScene().getWindow();
                mainMenu.setScene(mainMenuScene);
                mainMenu.show();
            }
            else{
                alert.showAndWait();
            }
    }
}
