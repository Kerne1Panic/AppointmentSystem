package Main;

import Util.DBConnection;
import Util.DBStatement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

/**
 * @author josealvarezpulido
 * This is the Main Class of this application. It sets up the primary stage, and also has the public static void main.
 */
public class jdbcApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/LogInView.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {
        //This line sets the default language to french so that the program does not need to restart to change the language.
        Locale.setDefault(new Locale("fr","CA"));
        /*
        Checking to see if Default language is supported
         */
        try{
            if(!Locale.getDefault().getLanguage().equals("fr") && !Locale.getDefault().getLanguage().equals("en")){
                Locale.setDefault(new Locale("en"));
                System.out.println("Default Language not supported");
            }
        }catch (Exception ignored){ }

        Connection connection = DBConnection.startConnection();

        DBStatement.setStatement(connection); //create statement object
        Statement statement = DBStatement.getStatement(); //get statement reference

        launch(args);

        DBConnection.closeConnection();
    }
}
