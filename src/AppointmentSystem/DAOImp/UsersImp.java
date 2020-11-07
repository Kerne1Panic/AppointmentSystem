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
 * Implements User Model  used to perform CRUD operations to the Database data.
 */
public class UsersImp {

    //static attribute used to store the extracted and created Objects of the Users class.
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
        if(users != null){
            users.clear();
        }
        final String sqlStatement = "SELECT * FROM users";
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
     * @return userFound local attribute used to store the extract and create the matching User_Name into a Object of the Users class.
     */
    public static Users getUser(String username) throws SQLException {
        Users userFound;
        final String sqlStatement = "SELECT * FROM users WHERE User_Name ='" + username + "'";
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
