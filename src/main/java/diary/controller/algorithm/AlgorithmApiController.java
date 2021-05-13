package diary.controller.algorithm;

import diary.dto.algorithm.Algorithm;
import diary.param.AlgorithmParam;
import diary.service.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDateTime;
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
    public List<Integer> getPageNums(@RequestParam(name="date", required = false)
                                         @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDateTime date) {

        System.out.println(date);
        return null;
    }

}
