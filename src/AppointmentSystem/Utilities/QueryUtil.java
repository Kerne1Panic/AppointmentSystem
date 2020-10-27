package AppointmentSystem.Utilities;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static AppointmentSystem.Utilities.DBConnection.connect;

/**
 * @author Jose Alvarez Pulido
 * Utility used to set a prepared statement and get a prepared statement.
 */
public class QueryUtil {
    //reference to the prepared statement.
    private static PreparedStatement preparedStatement;

    /**
     * This method creates a statement using a Connection. Using throws SQLExceptions for exception handling.
     * @throws SQLException
     */
    public static void setPreparedStatement(String SQLQuery) throws SQLException {
        preparedStatement = connect.prepareStatement(SQLQuery);
    }

    /**
     * This method returns the statement. The statement is created in the setStatement method.
     * @return statement
     */
    public static PreparedStatement getPreparedStatement()
    {
        return preparedStatement;
    }
}
