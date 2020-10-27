package AppointmentSystem;

import AppointmentSystem.DAOImp.UsersImp;
import AppointmentSystem.Utilities.DBConnection;
import AppointmentSystem.Utilities.QueryUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Locale;

import static java.lang.System.exit;

/**
 * @author josealvarezpulido
 * This is the Main Class, it sets up the primary stage, and also has the public static void main.
 */
public class AppointmentSystemApp extends Application {
    /**
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
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        DBConnection.startConnection();
        //This line sets the default language to french so that the program does not need to restart to change the language.
        //Locale.setDefault(new Locale("fr"));

        /*
        Checking to see if Default language is supported.
         */
        if(!Locale.getDefault().getLanguage().equals("fr") && !Locale.getDefault().getLanguage().equals("en"))
        {
            System.out.println("Language pack not supported. Supported languages include 'en' and 'fr'\n");
            DBConnection.closeConnection();
            exit(0);

        } else {

            launch(args);
        }

        DBConnection.closeConnection();
    }
}
