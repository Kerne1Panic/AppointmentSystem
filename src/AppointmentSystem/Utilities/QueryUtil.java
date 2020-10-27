package AppointmentSystem.Utilities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static AppointmentSystem.Utilities.DBConnection.connect;

/**
 * @author Jose Alvarez Pulido
 * Utility used to set a prepared statement and get a prepared statement.
 */
public class QueryUtil {
    //reference to the prepared statement.
    private static PreparedStatement preparedStatement;

    /**
     * This method creates a statement using a static attribute connect created in DBConnection Class, and a SQLQuery String to input the define the commands set to be executed. Using throws SQLExceptions for exception handling.
     * @throws SQLException to handle exceptions if a sql string is invalid.
     */
    public static void setPreparedStatement(String SQLQuery) throws SQLException {
        preparedStatement = connect.prepareStatement(SQLQuery);
    }

    /**
     * This method returns the statement. The statement is created in the setStatement method.
     * @return preparedStatement, returns the PreparedStatement created in the static mehod setPreparedStatement(String SQLQuery).
     */
    public static PreparedStatement getPreparedStatement()
    {
        return preparedStatement;
    }
}
