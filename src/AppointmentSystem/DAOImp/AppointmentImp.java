package AppointmentSystem.DAOImp;

import AppointmentSystem.DAOInterface.AppointmentInt;
import AppointmentSystem.Model.Appointments;
import AppointmentSystem.Utilities.QueryUtil;
import AppointmentSystem.Utilities.TimeUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

/**
 * @author josealvarezpulido
 */
public class AppointmentImp implements AppointmentInt {

    static ObservableList<Appointments> appointments = FXCollections.observableArrayList();



    public static ObservableList<Appointments> getAllAppointments() {
        final String sqlStatement = "SELECT * FROM appointments";
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

                Appointments appointmentsFound = new Appointments(appointmentId,title,description,location,type,start,end,createDate,createdBy,lastUpdate,lastUpdateBy,customerId,userId,contactId);
                appointments.add(appointmentsFound);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return appointments;
    }


    public void updateAppointments() {

    }


    public void deleteAppointments() {

    }

    public void addAppointments() {

    }
}
