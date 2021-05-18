package diary.controller.algorithm;

import diary.dto.algorithm.Algorithm;
import diary.dto.algorithm.FileInfo;
import diary.param.AlgorithmParam;
import diary.service.AlgorithmService;
import diary.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/algorithm")
public class AlgorithmApiController {

    @Autowired
    AlgorithmService algorithmService;

    private final String fileRoot = "/home/seungkyun/Web/diary/files/";

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

    @PostMapping(path="/")
    public int addAlgorithm(@RequestBody AlgorithmParam param) throws ParseException {
        Algorithm algorithm = param.toAlgorithm();
        return algorithmService.add(algorithm);
    }

    @PostMapping(path="/file")
    public void addFile(@RequestParam(name = "algorithmId") int algorithmId,
                       @RequestParam(name = "file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String saveFileName = fileRoot + fileName;
        String contentType = file.getContentType();

        algorithmService.addFileInfo(algorithmId, fileName, saveFileName, contentType);
        Utility.storeFile(fileRoot, file);
    }

    @GetMapping(path="/file/{id}")
    public void downloadFile(HttpServletResponse response, @PathVariable(name="id", required = true) int id) {
        FileInfo fileInfo = algorithmService.getFileInfo(id);
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

    @PutMapping(path="/{id}")
    public int updateAlgorithm(@RequestBody AlgorithmParam param) throws ParseException {
        Algorithm algorithm = param.toAlgorithm();
        return algorithmService.update(algorithm);
    }

    @PutMapping(path="/file/{id}")
    public void updateFile(@RequestParam(name="id") int fileId,
                           @RequestParam(name="algorithmId") int algorithmId,
                           @RequestParam(name="file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String saveFileName = fileRoot + fileName;
        String contentType = file.getContentType();

        algorithmService.updateFileInfo(algorithmId, fileName, saveFileName, contentType);
    }

    @DeleteMapping(path="/{id}")
    public int deleteAlgorithm(@PathVariable(name="id", required = true) int id) {
        return algorithmService.delete(id);
    }

    @GetMapping(path="/pagenum")
    public List<Integer> getPageNumbers(@RequestParam(name="date", required = true)
                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return algorithmService.getPageNumbers(date, date.plusMonths(1));
    }

}
