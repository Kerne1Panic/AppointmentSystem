package AppointmentSystem.Utilities;

import java.time.*;

public class TimeUtil
{
public static ZonedDateTime mergeDateTime(LocalDate date, LocalTime time)
{
    LocalDateTime dateTime= LocalDateTime.of(date,time);
    ZoneId zoneId = ZoneId.of("America/New_York");
    ZonedDateTime dateTimeZone = ZonedDateTime.of(dateTime,zoneId);
    return dateTimeZone;
}
    public static ZonedDateTime mergeDateTime(LocalDateTime localDateTime)
    {
        ZoneId zoneId = ZoneId.of("America/New_York");
        ZonedDateTime dateTimeZone = ZonedDateTime.of(localDateTime,zoneId);
        return dateTimeZone;
    }

}
