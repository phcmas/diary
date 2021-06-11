package diary.utility;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    public static void storeFile(String fileRoot, MultipartFile file) {
        int readCount = 0;
        String saveFileName = fileRoot + file.getOriginalFilename();
        byte[] buffer = new byte[1024];

        try (FileOutputStream fos = new FileOutputStream(saveFileName);
             InputStream is = file.getInputStream();) {
            while ((readCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, readCount);
            }
        } catch (Exception e) {
            throw new RuntimeException("File Save Error");
        }
    }

    public static void deleteFile(String saveFileName) {
        File file = new File(saveFileName);
        file.delete();
    }

    public static String getDateStr(LocalDate date) {
        String[] strs = date.toString().split("-");
        return strs[1]+ "/" + strs[2];
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

}
