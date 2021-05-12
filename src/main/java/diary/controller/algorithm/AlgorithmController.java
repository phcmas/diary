package diary.controller.algorithm;

import diary.dto.algorithm.Algorithm;
import diary.dto.algorithm.AlgorithmCard;
import diary.service.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/algorithm")
public class AlgorithmController {

    @Autowired
    AlgorithmService algorithmService;

    @GetMapping("/cards")
    public String showCards(@RequestParam(name = "year", required = true, defaultValue = "2021-05") String year,
                            @RequestParam(name = "start", required = true, defaultValue = "1") int pageNum,
                            Model model) {
        List<AlgorithmCard> algorithmCards = new ArrayList<>();
        model.addAttribute("algorithmCards", algorithmCards);
        model.addAttribute("year", year);
        return "/algorithm/cards";
    }

    @GetMapping("/lookup/{id}")
    public String showAlgorithm(@PathVariable(name="id") int id, Model model) {
        Algorithm algorithm = algorithmService.get(id);
        model.addAttribute("algorithm", algorithm.toAlgorithmParam());
        return "/algorithm/lookup";
    }

    @GetMapping("/registration")
    public String addAlgorithm() {
        return "/algorithm/registration";
    }

    @GetMapping("/modification/{id}")
    public String modifyAlgorithm(@PathVariable(name="id") int id, Model model) {
        Algorithm algorithm = algorithmService.get(id);
        model.addAttribute("algorithm", algorithm.toAlgorithmParam());
        return "/algorithm/modification";
    }

}
