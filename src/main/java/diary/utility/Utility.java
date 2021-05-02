package diary.utility;

import diary.dto.enums.ProjectType;
import diary.dto.enums.UserAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Utility {

    public static LocalDateTime convert(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date convert(LocalDateTime localDateTime) {
        return java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String getFormat(LocalDateTime localDateTime) {
        Date date = convert(localDateTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String getCurrentUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;

        return userDetails.getUsername();
    }

    public static ProjectType getProjectType(String projectType) {
        return ProjectType.valueOf(projectType);
    }

    public static UserAuthority getUserAuthority(String userAuthority) {
        return UserAuthority.valueOf(userAuthority);
    }

}
