package diary.controller.algorithm;

import diary.dto.algorithm.Algorithm;
import diary.dto.algorithm.AlgorithmCard;
import diary.dto.algorithm.FileInfo;
import diary.param.AlgorithmCardParam;
import diary.service.AlgorithmService;
import diary.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/algorithm")
public class AlgorithmController {

    @Autowired
    AlgorithmService algorithmService;

    @Autowired
    FileService fileService;

    @GetMapping("/cards")
    public String showCards(@RequestParam(name = "date", required = false, defaultValue = "2021-05-01")
                                @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate date,
                            @RequestParam(name = "start", required = true, defaultValue = "1") int pageNum,
                            Model model) throws ParseException {
        List<AlgorithmCard> cards = algorithmService.getCards(pageNum, date, date.plusMonths(1));
        List<AlgorithmCardParam> params = new ArrayList<>();

        for (AlgorithmCard card : cards) {
            params.add(card.toAlgorithmCardParam());
        }

        model.addAttribute("algorithmCards", params);
        model.addAttribute("date", date);
        return "/algorithm/cards";
    }

    @GetMapping("/{id}/lookup")
    public String showAlgorithm(@PathVariable(name="id") int id, Model model) {
        Algorithm algorithm = algorithmService.get(id);
        FileInfo fileInfo = fileService.getByAlgorithmId(id);
        model.addAttribute("algorithm", algorithm.toAlgorithmParam(fileInfo));

        return "/algorithm/lookup";
    }

    @GetMapping("/registration")
    public String addAlgorithm() {
        return "/algorithm/registration";
    }

    @GetMapping("/{id}/modification")
    public String modifyAlgorithm(@PathVariable(name="id") int id, Model model) {
        Algorithm algorithm = algorithmService.get(id);
        FileInfo fileInfo = fileService.getByAlgorithmId(id);
        model.addAttribute("algorithm", algorithm.toAlgorithmParam(fileInfo));

        return "/algorithm/modification";
    }

}
