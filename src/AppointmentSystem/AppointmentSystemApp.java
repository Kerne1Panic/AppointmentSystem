package AppointmentSystem;

import AppointmentSystem.Utilities.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Locale;
import java.util.spi.LocaleNameProvider;

import static java.lang.System.exit;

/**
 * @author josealvarezpulido
 * Main Class sets up the primary stage, and also has the public static void main.
 */
public class AppointmentSystemApp extends Application {
    /**
     * FXML resources are used to set the primary stage for the GUI application.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View_Controllers/LogInView.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Establishes connection with the database using the jdbc url, sets the Resource bundle to the supported language, Launch the GUI.
     * Language support includes english and french.
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {

        //Static function establishes the jdbc connection using the url, user name and password.
        DBConnection.startConnection();
        //This line sets the default language to french so that the program does not need to restart to change the language.
        //Locale.setDefault(new Locale("fr"));
        /*
        Checking to see if Default language is supported. If the Default language is not supported the application closes.
         */
        if(!Locale.getDefault().getLanguage().equals("fr") && !Locale.getDefault().getLanguage().equals("en"))
        {
            System.out.println("Language pack not supported. Supported languages include 'en' and 'fr'\n");
            DBConnection.closeConnection();
            exit(0);

        } else {
            launch(args);
        }
        //closes the DB connection using the static method.
        DBConnection.closeConnection();
    }
}
