package AppointmentSystem.DAOImp;

import AppointmentSystem.Model.Countries;
import AppointmentSystem.Utilities.QueryUtil;
import AppointmentSystem.Utilities.TimeUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.*;
/**
 * @author josealvarezpulido
 * Used to perform the Read operations with the data base of the Countries Class Model.
 */
public class CountriesImp {
    /**
     * List of all countries extracted from the database, it is set and returned in the getAllCountries static method.
     */
    static ObservableList<Countries> countries = FXCollections.observableArrayList();
    /**
     * Read, the static method used to read the data from the database and create  Countries model object.
     * @return ObservableList countries
     */
    public static ObservableList<Countries> getAllCountries() {
        /**
         * sqlStatement is a string that contains the SQL statement that will be executed.
         */
        final String sqlStatement = "SELECT * FROM countries";
        if(countries != null){
            countries.clear();
        }
        /**
         * try catch block used for exception handling.
         */
        try{
            QueryUtil.setPreparedStatement(sqlStatement);
            PreparedStatement ps = QueryUtil.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            // while next()
            while (rs.next())
            {
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                LocalDate date = rs.getDate("Create_Date").toLocalDate();
                LocalTime time = rs.getTime("Create_Date").toLocalTime();
                ZonedDateTime createDate = TimeUtil.convertBack(ZonedDateTime.of(LocalDateTime.of(date,time),ZoneId.of("UTC")));
                String createdBy = rs.getString("Created_By");
                LocalDateTime update = rs.getTimestamp("Last_Update").toLocalDateTime();
                ZonedDateTime lastUpdate = TimeUtil.convertBack(ZonedDateTime.of(update,ZoneId.of("UTC")));
                String lastUpdatedBy = rs.getString("Last_Updated_By");

                Countries countriesFound = new Countries(countryId,countryName,createDate,createdBy,lastUpdate,lastUpdatedBy);
                countries.add(countriesFound);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return countries;
    }
}
