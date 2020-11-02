package AppointmentSystem.DAOImp;

import AppointmentSystem.Model.Divisions;
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

public class DivisionsImp  {

    static ObservableList<Divisions> divisions = FXCollections.observableArrayList();

    public static ObservableList<Divisions> getAllDivisions() {
        final String sqlStatement = "SELECT * FROM first_level_divisions";
        if(divisions != null){
            divisions.clear();
        }
        try{
            QueryUtil.setPreparedStatement(sqlStatement);
            PreparedStatement ps = QueryUtil.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            //while next()
            while (rs.next())
            {
                int divisionId = rs.getInt("Division_ID");
                String DivisionName = rs.getString("Division");
                LocalDate date = rs.getDate("Create_Date").toLocalDate();
                LocalTime time = rs.getTime("Create_Date").toLocalTime();
                ZonedDateTime createDate = TimeUtil.mergeDateTime(date,time);
                String createdBy = rs.getString("Created_By");
                LocalDateTime update = rs.getTimestamp("Last_Update").toLocalDateTime();
                ZonedDateTime lastUpdate = TimeUtil.mergeDateTime(update);
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int countryId = rs.getInt("Country_ID");

                Divisions divisionsFound = new Divisions(divisionId,DivisionName,createDate,createdBy,lastUpdate,lastUpdatedBy,countryId);
                divisions.add(divisionsFound);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return divisions;
    }

    public void updateDivisions() {

    }

    public void deleteDivisions() {

    }

    public void addDivisions() {

    }
}
