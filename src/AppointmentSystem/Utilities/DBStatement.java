package AppointmentSystem.Utilities;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Jose Alvarez Pulido
 *
 */
public class DBStatement {
    //reference to the statement.
    private static Statement statement;

    /**
     * This method creates a statement using a Connection. Using throws SQLExceptions for exception handling.
     * @param connection
     * @throws SQLException
     */
    public static void setStatement(Connection connection) throws SQLException {
        statement = connection.createStatement();
    }

    /**
     * This method returns the statement. The statement is created in the setStatement method.
     * @return statement
     */
    public static Statement getStatement()
    {
        return statement;
    }
}
