package AppointmentSystem.DAOImp;

import AppointmentSystem.Model.Appointments;
import AppointmentSystem.Utilities.QueryUtil;
import AppointmentSystem.Utilities.TimeUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

/**
 * @author josealvarezpulido
 */
public class AppointmentImp {

    static ObservableList<Appointments> appointments = FXCollections.observableArrayList();

    public static ObservableList<Appointments> getAllAppointments() {
        final String sqlStatement = "SELECT * FROM appointments, customers, users, contacts " +
                "WHERE appointments.Customer_ID = customers.Customer_ID " +
                "AND appointments.user_ID = users.user_ID " +
                "AND appointments.contact_ID = contacts.contact_ID";
        if(appointments != null){
            appointments.clear();
        }
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
                ZonedDateTime start = TimeUtil.mergeDateTime(startDate,startTime);
                LocalDate endDate = rs.getDate("End").toLocalDate();
                LocalTime endTime = rs.getTime("End").toLocalTime();
                ZonedDateTime end = TimeUtil.mergeDateTime(endDate,endTime);
                LocalDate create_date = rs.getDate("Create_Date").toLocalDate();
                LocalTime createTime = rs.getTime("Create_Date").toLocalTime();
                ZonedDateTime createDate = TimeUtil.mergeDateTime(create_date,createTime);
                String createdBy = rs.getString("Created_By");
                LocalDateTime last = rs.getTimestamp("Last_Update").toLocalDateTime();
                ZonedDateTime lastUpdate = TimeUtil.mergeDateTime(last);
                String lastUpdateBy = rs.getString("Last_Updated_By");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                String customerName = rs.getString("Customer_Name");
                String userName = rs.getString("User_Name");
                String contactName = rs.getString("Contact_Name");

                Appointments appointmentsFound = new Appointments(appointmentId,title,description,location,type,start,end,createDate,createdBy,lastUpdate,lastUpdateBy,customerId,userId,contactId,customerName,userName,contactName);
                appointments.add(appointmentsFound);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return appointments;
    }


    public static void updateAppointments(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime lastUpdate, String updatedBy, int customerId, int userId, int contactId, int appointmentId) {
        String sqlStatement = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ? ";
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


    public static void deleteAppointments(int appointmentId) {
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

    public static void addAppointments(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime created, String createdBy, int customerId, int userId, int contactId) {
        String sqlStatement = "INSERT INTO appointments(Title,Description,Location,Type,Start,End,Create_Date,Created_By,Customer_ID,User_ID,Contact_ID)"+
                "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
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
