package diary.controller.algorithm;

import diary.dto.algorithm.Algorithm;
import diary.param.AlgorithmParam;
import diary.service.AlgorithmService;
import diary.service.FileService;
import diary.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/algorithm")
public class AlgorithmApiController {

    @Autowired
    AlgorithmService algorithmService;

    @Autowired
    FileService fileService;

    @PostMapping(path="/")
    public int addAlgorithm(@RequestBody AlgorithmParam param) throws ParseException {
        Algorithm algorithm = param.toAlgorithm();
        return algorithmService.add(algorithm);
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

    @GetMapping(path="/pagenum/{date}")
    public List<Integer> getPageNumbers(@PathVariable(name="date", required = true)
                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return algorithmService.getPageNumbers(date, date.plusMonths(1));
    }

    @GetMapping(path="/{id}/authority")
    public boolean getAuthority (@PathVariable(name="id") int id) {
        String author = algorithmService.getAuthor(id);
        String currentUser = Utility.getCurrentUserName();
        return author.equals(currentUser);
    }

}
