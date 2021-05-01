package diary.utility;

import diary.dto.enums.ProjectType;
import diary.dto.enums.UserAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Utility {

    public static LocalDateTime convertDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
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
