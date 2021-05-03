package diary.utility;

import diary.dto.enums.ProjectType;
import diary.dto.enums.UserAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Utility {

    public static LocalDateTime convert(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    //public static Date convert(LocalDateTime localDateTime) {
    //    return java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    //}

    public static LocalDateTime convert(String dateString) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        return convert(date);
    }

    public static String convert(LocalDateTime localDateTime) {
        Date date = java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    public static Date yearToDate(String year) throws ParseException {
        String dateString = year + "-01-01";
        return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
    }

    public static Date addTime(Date date, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    public static String getCurrentUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;

        return userDetails.getUsername();
    }

    public static String cutString(String string, int length) {
        String result = "";
        if (string.length() <= length) {
            result = string;
        } else {
            result = string.substring(0, length-1) + "...";
        }

        return result;
    }

    public static ProjectType getProjectType(String projectType) {
        return ProjectType.valueOf(projectType);
    }

    public static UserAuthority getUserAuthority(String userAuthority) {
        return UserAuthority.valueOf(userAuthority);
    }

}
