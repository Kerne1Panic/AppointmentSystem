package AppointmentSystem.Utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;

/**
 * @author josealvarezpulido
 * A utility class used to convert and create ZonedDateTime Objects. Usually used when extracting dates and times from SQL database.
 */
public class TimeUtil
{
    /**
     * Converts from the computer detected time to UTC time from the Database.
     * @param defaultTime the LocalDatetime of the user.
     * @param defaultId the Zone ID of the user.
     * @return a converted LocalDateTime
     */
    public static LocalDateTime convertToUTC(LocalDateTime defaultTime, ZoneId defaultId){
        ZoneId UTC = ZoneId.of("UTC");
        ZonedDateTime defaultZDT = ZonedDateTime.of(defaultTime, defaultId);
        ZonedDateTime defaultToUTC = defaultZDT.withZoneSameInstant(UTC);
        return defaultToUTC.toLocalDateTime();
    }
    /**
     * Converts Zoned Date Time back into a Local Time in the users Local zone.
     * @param zonedDateTime The attribute held in the Appointments Zoned Date Time
     * @return a ZonedDateTime using the Zone Id of the user.
     */
    public static ZonedDateTime convertBack(ZonedDateTime zonedDateTime)
    {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        return zonedDateTime.withZoneSameInstant(defaultZoneId);
    }

    /**
     * returns an observable list of LocalTime(s) incrementing by user selected increment in minutes from the start to end time, the end time must be one increment over the intended end time.
     * @param start
     * @param end
     * @param increment
     * @return
     */
    public static ObservableList<LocalTime> getTimes(LocalTime start, LocalTime end, int increment){
        ObservableList<LocalTime>  openHours = FXCollections.observableArrayList();
        if(start.isBefore(end)){
            while(start.isBefore(end)){
                openHours.add(start);
                start = start.plusMinutes(increment);
            }
            return openHours;
        }
        else return null;
    }
}
