package AppointmentSystem.DAOImp;

import AppointmentSystem.Model.Users;
import AppointmentSystem.Utilities.QueryUtil;
import AppointmentSystem.Utilities.TimeUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;

/**
 * @author josealvarezpulido
 * Used to perform the CRUD operations with the data base of the Users Class Model.
 * Create Read Update Delete.
 */
public class UsersImp {
    /**
     * List of all users extracted from the database, it is set and returned in the getAllUsers static method.
     */
    static ObservableList<Users> users = FXCollections.observableArrayList();
    /**
     * userLoggedIn is set in the LogInView using the LogInController inside the LogIn actionEvent.
     * Used to keep track of which user is logged in to the System.
     */
    private static String userLoggedIn;

    /**
     * Reads Data from the database using a PreparedStatement sqlStatement which contains a SELECT SQL command.
     * uses try catch block to handle a wrong SQL statement, here the statement is final, however in the event of retrieving data from user it would help catch user error.
     * @return users static attribute used to store the extracted and created Objects of the Users class.
     */
    public static ObservableList<Users> getAllUsers() {
        /**
         * sqlStatement is a string that contains the SQL statement that will be executed.
         */
        final String sqlStatement = "SELECT * FROM users";
        /**
         * used to clear the list if the list is not clear, this is because a possible error when reusing the static method causes the same list to be appended again.
         */
        if(users != null){
            users.clear();
        }
        /**
         * Try catch block used to catch SQL Exceptions, catching wrong sql statements so the app does not crash in that event.
         */
        try{
            QueryUtil.setPreparedStatement(sqlStatement);
            PreparedStatement ps = QueryUtil.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            //while next() loop
            while (rs.next())
            {
                //Values that will used to create a User
                int userId = rs.getInt("User_ID");
                String user = rs.getString("User_Name");
                String password = rs.getString("Password");
                LocalDate date = rs.getDate("Create_Date").toLocalDate();
                LocalTime time = rs.getTime("Create_Date").toLocalTime();
                ZonedDateTime createDate = TimeUtil.convertBack(ZonedDateTime.of(LocalDateTime.of(date,time), ZoneId.of("UTC"))) ;
                String createdBy = rs.getString("Created_By");
                LocalDateTime last = rs.getTimestamp("Last_Update").toLocalDateTime();
                ZonedDateTime lastUpdate = TimeUtil.convertBack(ZonedDateTime.of(last,ZoneId.of("UTC")));
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                Users userFound = new Users(userId,user,password,createDate,createdBy,lastUpdate,lastUpdatedBy);
                users.add(userFound);

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    /**
     * Sets the private static attribute userLoggedIn.
     * @param username is used to set the userLoggedIn attribute.
     */
    public static void setUserLoggedIn(String username){
        userLoggedIn = username;
    }

    /**
     * gets the userLoggedIn private static attribute.
     * @return userLoggedIn
     */
    public static String getUserLoggedIn(){
        return userLoggedIn;
    }


    /**
     * Reads Data from the database using a PreparedStatement sqlStatement which contains a SELECT WHERE SQL command to retrieve a specific User_Name.
     * uses try catch block to handle a wrong SQL statement, here the statement is final, however in the event of retrieving data from user it would help catch user error.
     * @param username username used to find the User from database.
     * @return userFound local attribute used to store the extract and create the matching User_Name into a Object of the Users class.
     * @throws SQLException used to throw SQL exceptions that may cause errors.
     */
    public static Users getUser(String username) throws SQLException {
        Users userFound;
        /**
         * sqlStatement is a string that contains the SQL statement that will be executed.
         */
        final String sqlStatement = "SELECT * FROM users WHERE User_Name ='" + username + "'";
        /**
         * Try catch block used to catch SQL Exceptions, catching wrong sql statements so the app does not crash in that event.
         */
        try{
            QueryUtil.setPreparedStatement(sqlStatement);
            PreparedStatement ps = QueryUtil.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            //while next() loop
            while (rs.next())
            {
                //Values that will used to create a User
                int userId = rs.getInt("User_ID");
                String user = rs.getString("User_Name");
                String password = rs.getString("Password");
                LocalDate date = rs.getDate("Create_Date").toLocalDate();
                LocalTime time = rs.getTime("Create_Date").toLocalTime();
                ZonedDateTime createDate = TimeUtil.convertBack(ZonedDateTime.of(LocalDateTime.of(date,time),ZoneId.of("UTC")));
                String createdBy = rs.getString("Created_By");
                LocalDateTime last = rs.getTimestamp("Last_Update").toLocalDateTime();
                ZonedDateTime lastUpdate = TimeUtil.convertBack(ZonedDateTime.of(last, ZoneId.of("UTC")));
                String lastUpdatedBy = rs.getString("Last_Updated_By");

                userFound = new Users(userId,user,password,createDate,createdBy,lastUpdate,lastUpdatedBy);
                return userFound;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
