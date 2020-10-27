package AppointmentSystem.DAOImp;

import AppointmentSystem.DAOInterface.UsersInt;
import AppointmentSystem.Model.Users;
import AppointmentSystem.Utilities.QueryUtil;
import AppointmentSystem.Utilities.TimeUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

public class UsersImp implements UsersInt {

    public ObservableList<Users> users = FXCollections.observableArrayList();

//    public UsersImp(ObservableList<Users> users) {
//        this.users = users;
//    }

    public ObservableList<Users> getAllUsers() {
        return null;
    }



    public void updateUsers() {

    }

    public void deleteUsers() {

    }

    @Override
    public void addUsers() {

    }

    public static Users getUser(String username) throws SQLException {
        Users userFound;
        String sqlStatement = "SELECT * FROM users WHERE User_Name ='" + username + "'";
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
                ZonedDateTime createDate = TimeUtil.mergeDateTime(date,time);
                String createdBy = rs.getString("Created_By");
                LocalDateTime last = rs.getTimestamp("Last_Update").toLocalDateTime();
                ZonedDateTime lastUpdate = TimeUtil.mergeDateTime(last);
                String lastUpdatedBy = rs.getString("Last_Updated_By");

                userFound = new Users(userId,user,password,createDate,createdBy,lastUpdate,lastUpdatedBy);
                System.out.println(userFound.getCreateDate());
                return userFound;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * This method adds newUser to the users List.
     * @param newUser is the User that is being added.
     */
    public void addUsers(Users newUser) {
        users.add(newUser);
    }
}
