package AppointmentSystem.DAOImp;

import AppointmentSystem.Model.Countries;
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

public class CountriesImp {

    static ObservableList<Countries> countries = FXCollections.observableArrayList();

    public static ObservableList<Countries> getAllCountries() {
        final String sqlStatement = "SELECT * FROM countries";
        if(countries != null){
            countries.clear();
        }
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
                ZonedDateTime createDate = TimeUtil.mergeDateTime(date,time);
                String createdBy = rs.getString("Created_By");
                LocalDateTime update = rs.getTimestamp("Last_Update").toLocalDateTime();
                ZonedDateTime lastUpdate = TimeUtil.mergeDateTime(update);
                String lastUpdatedBy = rs.getString("Last_Updated_By");

                Countries countriesFound = new Countries(countryId,countryName,createDate,createdBy,lastUpdate,lastUpdatedBy);
                countries.add(countriesFound);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return countries;
    }

    public void updateCountries() {

    }

    public void deleteCountries() {

    }

    public void addCountries() {

    }
}
