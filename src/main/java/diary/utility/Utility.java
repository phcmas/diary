package diary.utility;

import diary.dto.projects.ProjectMember;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utility {

    public static List<Integer> getPageList(int count, int cardLimit) {
        List<Integer> pageNumbers = new ArrayList<>();

        if (count <= cardLimit) return pageNumbers;
        int maxPage = count / cardLimit;

        if (count % cardLimit > 0) maxPage++;

        for (int i = 1; i <= maxPage; ++i) {
            pageNumbers.add(i);
        }

        return pageNumbers;
    }

    public static LocalDate convert(Date date) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = date.toInstant();
        return instant.atZone(defaultZoneId).toLocalDate();
    }

    public static void storeFile(String path, MultipartFile file) {
        int readCount = 0;
        byte[] buffer = new byte[1024];

        try (FileOutputStream fos = new FileOutputStream(path + file.getOriginalFilename());
             InputStream is = file.getInputStream();) {
            while ((readCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, readCount);
            }
        } catch (Exception e) {
            throw new RuntimeException("File Save Error");
        }
    }

    //public static LocalDateTime convert(Date date) {
    //    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    //}

    //public static Date convert(LocalDateTime localDateTime) {
    //    return java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    //}

    //public static LocalDate convert(String dateString) throws ParseException {
    //    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
    //    return convert(date);
    //}

    //public static String convert(LocalDateTime localDateTime) {
    //    Date date = java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    //    SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
    //    return simpleDateFormat.format(date);
    //}

    //public static Date getDate(String year, String month, String day) throws ParseException {
    //    String dateString = year + "-" + month + "-" + day;
    //    return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
    //}

    //public static Date addTime(Date date, int year, int month, int day) {
    //    Calendar calendar = Calendar.getInstance();
    //    calendar.setTime(date);
    //    calendar.add(Calendar.YEAR, year);
    //    calendar.add(Calendar.MONTH, month);
    //    calendar.add(Calendar.DATE, day);
    //    return calendar.getTime();
    //}

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

}
