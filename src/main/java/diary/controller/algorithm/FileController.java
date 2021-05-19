package diary.controller.algorithm;

import diary.dto.algorithm.FileInfo;
import diary.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;

@RestController
@RequestMapping(path="/algorithm")
public class FileController {

    @Autowired
    FileService fileService;

    void setResponseHeader(HttpServletResponse response, FileInfo fileInfo) {
        String fileName = fileInfo.getFileName();
        String saveFileName = fileInfo.getSaveFileName();
        String contentType = fileInfo.getContentType();

        response.setHeader("Content-Disposition", "attachment; filename=\""+ fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", contentType);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1;");
    }

    @PostMapping(path="/file")
    public int addFile(@RequestParam(name = "algorithmId") int algorithmId,
                        @RequestParam(name = "file") MultipartFile file) {
        return fileService.add(algorithmId, file);
    }

    @DeleteMapping(path="file/{id}")
    public int deleteFile(@PathVariable(name="id") int id) {
        return fileService.delete(id);
    }

    @PutMapping(path="/file")
    public int updateFile(@RequestParam(name="id") int id,
                           @RequestParam(name="file") MultipartFile file) {
        return fileService.update(id, file);
    }

    @GetMapping(path="/file/{id}")
    public void downloadFile(HttpServletResponse response, @PathVariable(name="id", required = true) int id) {
        FileInfo fileInfo = fileService.get(id);
        setResponseHeader(response, fileInfo);

        try (FileInputStream fis = new FileInputStream(fileInfo.getSaveFileName());
             OutputStream out = response.getOutputStream();) {
            int readCount = 0;
            byte[] buffer = new byte[1024];

            while ((readCount = fis.read(buffer)) != -1) {
                out.write(buffer,0, readCount);
            }
        } catch (Exception ex) {
            throw new RuntimeException("File Save Error");
        }
    }

}
