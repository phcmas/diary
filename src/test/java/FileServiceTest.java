import diary.config.ApplicationConfig;
import diary.config.SecurityConfig;
import diary.service.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class})
public class FileServiceTest {

    @Autowired
    FileService fileService;

    @Test
    public void storeFileTest() throws FileNotFoundException {
        MultipartFile file = new MultipartFile() {
            @Override
            public String getName() {
                return "test";
            }

            @Override
            public String getOriginalFilename() {
                return "test";
            }

            @Override
            public String getContentType() {
                return "image/jpeg";
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 40687;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                File file = new File("/home/seungkyun/Web/diary/src/test/test.jpg");
                InputStream is = new FileInputStream(file);
                return is;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
            }
        };

        fileService.storeFile(file);
    }
}
