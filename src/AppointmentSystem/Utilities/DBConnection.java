package AppointmentSystem.Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Jose Alvarez Pulido
 * This class establishes a database connection to a uCertify Database using the protocol, vendorName, and ipAddress.
 */
public class DBConnection {
    //JDBC URL parts
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com/WJ07n6C";

    //JDBC URL is concatenated using the parts.
    private static final String jdbcURL = protocol + vendorName + ipAddress;

    //mySQL JDBC Driver and Connection Interface reference
    private static final String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";
    //connect is made as a static attribute so that it only needs to be created once, since the connection is not needed to change.
    static Connection connect;

    //username and password
    private static final String username = "U07n6C";
    private static final String password = "53689078158";

    /**
     * This method establishes the connection jdbc driver, using a try catch for exception handling.
     * It uses a Class.forName to get the Driver. Casting a  jdbc.Connection type to the sql.DriverManager to return the connection.
     * The DriverManager is used to get a connection to the database using the jdbc url, the username, and password.
     *
     * if the connection is successful a message is displayed in terminal, else the error message is displayed using the catch.
     *
     * @return conn. This is a mysql jdbc Connection Object.
     */
    public static Connection startConnection()
    {
        //try catch used to catch Classes that are not found or not defined.
        try
        {
            //try catch needed incase class not found.
            Class.forName(MYSQLJDBCDriver);
            connect = (Connection) DriverManager.getConnection(jdbcURL, username, password);

            //connection successful message.
            System.out.println("Connection Successful!\n");

        }catch (ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return connect;
    }

    /**
     * This method closes the connection to the database using the defined Connection object, no params or returns are needed.. The connection is closed using conn.close().
     * A try Catch block is used for exception handling.
     */
    public static void closeConnection()
    {
        //try catch used to catch SQL exceptions.
        try
        {
            connect.close();
            System.out.println("Connection Closed!\n");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
