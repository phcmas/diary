package diary.controller.algorithm;

import diary.dto.algorithm.Algorithm;
import diary.param.AlgorithmParam;
import diary.service.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path="/algorithm")
public class AlgorithmApiController {

    @Autowired
    AlgorithmService algorithmService;

    @PostMapping(path="/")
    public int addAlgorithm(@RequestBody AlgorithmParam param) throws ParseException {
        Algorithm algorithm = param.toAlgorithm();
        return algorithmService.add(algorithm);
    }

    @PostMapping(path="/file")
    public int addFile(@RequestParam(name = "algorithmId") int algorithmId,
                       @RequestParam(name = "file") MultipartFile file) {

        try (FileOutputStream fos = new FileOutputStream("/tmp/Web/" + file.getOriginalFilename());
             InputStream is = file.getInputStream();) {
            int readCount = 0;
            byte[] buffer = new byte[1024];
            while ((readCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, readCount);
            }
        } catch (Exception e) {
            throw new RuntimeException("file save error");
        }

        return 0;
    }

    @PutMapping(path="/{id}")
    public int updateAlgorithm(@RequestBody AlgorithmParam param) throws ParseException {
        Algorithm algorithm = param.toAlgorithm();
        return algorithmService.update(algorithm);
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
