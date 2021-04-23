package diary.utility;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Utility {
    public static LocalDateTime convertDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
