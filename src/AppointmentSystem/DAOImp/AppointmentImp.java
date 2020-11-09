package AppointmentSystem.DAOImp;

import AppointmentSystem.Model.Appointments;
import AppointmentSystem.Utilities.QueryUtil;
import AppointmentSystem.Utilities.TimeUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.*;

/**
 * @author josealvarezpulido
 * Used to perform the CRUD operations with the data base of the Appointment Class Model.
 * Create Read Update Delete.
 */
public class AppointmentImp {
    /**
     * List of all appointments extracted from the database, it is set and returned in the getAllAppointments static method.
     */
    static ObservableList<Appointments> appointments = FXCollections.observableArrayList();

    /**
     * Read, static method used to communicate with the database using jdbc, and uses a sql Select statement to read in all the values used to create an Appointment Object.
     * @return the static observable list appointments.
     */
    public static ObservableList<Appointments> getAllAppointments() {
        /**
         * sqlStatement is the string used to communicate with the database using an SQL preparedStatement.
         * This statement reads data from 4 tables(appointments, customers, users, contacts) to create a Join and get the customers name, users usersname, contact name.
         * These values are also stored in the appointment class object for setting UI with the actual names rather than the index or IDs.
         */
        final String sqlStatement = "SELECT * FROM appointments, customers, users, contacts " +
                "WHERE appointments.Customer_ID = customers.Customer_ID " +
                "AND appointments.user_ID = users.user_ID " +
                "AND appointments.contact_ID = contacts.contact_ID";
        /**
         * used to clear the list if the list is not clear, this is because a possible error when reusing the static method causes the same list to be appended again.
         */
        if(appointments != null){
            appointments.clear();
        }
        /**
         * Try catch block used to catch SQL Exceptions, catching wrong sql statements so the app does not crash in that event.
         */
        try{
            QueryUtil.setPreparedStatement(sqlStatement);
            PreparedStatement ps = QueryUtil.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            //while rs.next() Loop used to step through the data
            while(rs.next()){
                //values for Appointments extracted from result set
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDate startDate = rs.getDate("Start").toLocalDate();
                LocalTime startTime = rs.getTime("Start").toLocalTime();
                ZonedDateTime start = TimeUtil.convertBack(ZonedDateTime.of(LocalDateTime.of(startDate,startTime), ZoneId.of("UTC")));
                LocalDate endDate = rs.getDate("End").toLocalDate();
                LocalTime endTime = rs.getTime("End").toLocalTime();
                ZonedDateTime end = TimeUtil.convertBack(ZonedDateTime.of(LocalDateTime.of(endDate,endTime), ZoneId.of("UTC")));
                LocalDate create_date = rs.getDate("Create_Date").toLocalDate();
                LocalTime createTime = rs.getTime("Create_Date").toLocalTime();
                ZonedDateTime createDate = TimeUtil.convertBack(ZonedDateTime.of(LocalDateTime.of(create_date,createTime),ZoneId.of("UTC")));
                String createdBy = rs.getString("Created_By");
                LocalDateTime last = rs.getTimestamp("Last_Update").toLocalDateTime();
                ZonedDateTime lastUpdate = TimeUtil.convertBack(ZonedDateTime.of(last,ZoneId.of("UTC")));
                String lastUpdateBy = rs.getString("Last_Updated_By");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                String customerName = rs.getString("Customer_Name");
                String userName = rs.getString("User_Name");
                String contactName = rs.getString("Contact_Name");
                /**
                 * Appointments are defined and stored in the appointments ObservableList to be returned when calling this static function.
                 */
                Appointments appointmentsFound = new Appointments(appointmentId,title,description,location,TypesImp.getType(type),start,end,createDate,createdBy,lastUpdate,lastUpdateBy,customerId,userId,contactId,customerName,userName,contactName);
                appointments.add(appointmentsFound);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return appointments;
    }

    /**
     * Update, static update method used for Appointments in the database using the appointmentId attribute of the Appointment model object, The rest of the parameters are used to as the data that will be updated.
     * @param title title of the appointment
     * @param description description of the appointment
     * @param location location of the appointment
     * @param type type of appointment
     * @param start start date time in UTC
     * @param end end date time in UTC
     * @param lastUpdate update date time in UTC
     * @param updatedBy UserID of the user who updated the appointment
     * @param customerId the customerID of the customers appointment
     * @param userId userID for the user
     * @param contactId contanctID for the contact will respond to the user.
     * @param appointmentId This is the paramater used to determine which Appointment will be updated. It is extracted from a TableView using the SelectionModel.
     */
    public static void updateAppointments(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime lastUpdate, String updatedBy, int customerId, int userId, int contactId, int appointmentId) {
        /**
         * sqlStatement is a string of the SQL UPDATE Statement used to update the appointment in the database.
         */
        String sqlStatement = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ? ";
        /**
         * Try Catch block used for catching Exceptions.
         */
        try {
            QueryUtil.setPreparedStatement(sqlStatement);
            PreparedStatement ps = QueryUtil.getPreparedStatement();
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setTimestamp(7, Timestamp.valueOf(lastUpdate));
            ps.setString(8, updatedBy);
            ps.setInt(9, customerId);
            ps.setInt(10, userId);
            ps.setInt(11, contactId);
            ps.setInt(12, appointmentId);

            ps.execute();
        }catch (Exception e){
            System.out.println("Error Update: "+e.getMessage());
        }
    }

    /**
     * Delete ,static method used to delete appointments using a SQL delete statement.
     * @param appointmentId used to Delete appointment.
     */
    public static void deleteAppointments(int appointmentId) {
        /**
         * sqlStatement holds the String of the SQL Delete statement. uses the QueryUtil to set the prepared statement, declare a prepared statement and execute it.
         */
        String sqlStatement = "DELETE FROM appointments WHERE Appointment_ID = ?";
        try{
            QueryUtil.setPreparedStatement(sqlStatement);
            PreparedStatement ps = QueryUtil.getPreparedStatement();
            ps.setInt(1,appointmentId);
            ps.execute();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * Create, static method creates(adds) new appointments and stores them in the database using a SQL INSERT INTO statement.
     * The parameters are the values that will be used to create the new object.
     * @param title title of the appointment
     * @param description description of the appointment
     * @param location location of the appointment
     * @param type type of appointment
     * @param start start date time in UTC
     * @param end end date time in UTC
     * @param created Created date time in UTC
     * @param createdBy UserID of the user who created the appointment
     * @param customerId the customerID of the customers appointment
     * @param userId userID for the user
     * @param contactId contanctID for the contact will respond to the user.
     */
    public static void addAppointments(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime created, String createdBy, int customerId, int userId, int contactId) {
        /**
         * SQL Statement used to insert into the data base.
         */
        String sqlStatement = "INSERT INTO appointments(Title,Description,Location,Type,Start,End,Create_Date,Created_By,Customer_ID,User_ID,Contact_ID)"+
                "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        /**
         * try catch block used to catch exceptions.
         */
        try {
            QueryUtil.setPreparedStatement(sqlStatement);
            PreparedStatement ps = QueryUtil.getPreparedStatement();
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setTimestamp(7, Timestamp.valueOf(created));
            ps.setString(8, createdBy);
            ps.setInt(9, customerId);
            ps.setInt(10, userId);
            ps.setInt(11, contactId);

            ps.execute();
        }catch (Exception e){
            System.out.println("Error Update: "+e.getMessage());
        }
    }
}
