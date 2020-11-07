package AppointmentSystem.DAOImp;

import AppointmentSystem.Model.Divisions;
import AppointmentSystem.Utilities.QueryUtil;
import AppointmentSystem.Utilities.TimeUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.*;
/**
 * @author josealvarezpulido
 * Used to perform the Read operations with the data base of the Divisions Class Model.
 */
public class DivisionsImp  {
    /**
     * List of all divisions extracted from the database, it is set and returned in the getAllDivisions static method.
     */
    static ObservableList<Divisions> divisions = FXCollections.observableArrayList();
    /**
     * Read, the static method used to read the data from the database and create  Divisions model object.
     * @return ObservableList divisions
     */
    public static ObservableList<Divisions> getAllDivisions() {
        final String sqlStatement = "SELECT * FROM first_level_divisions";
        if(divisions != null){
            divisions.clear();
        }
        /**
         * try catch block used for exception handling.
         */
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
                ZonedDateTime createDate = TimeUtil.convertBack( ZonedDateTime.of(LocalDateTime.of(date,time), ZoneId.of("UTC")));
                String createdBy = rs.getString("Created_By");
                LocalDateTime update = rs.getTimestamp("Last_Update").toLocalDateTime();
                ZonedDateTime lastUpdate = TimeUtil.convertBack(ZonedDateTime.of(update,ZoneId.of("UTC")));
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
}
