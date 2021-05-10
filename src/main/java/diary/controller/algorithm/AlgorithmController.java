package diary.controller.algorithm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlgorithmController {

    @GetMapping("/algorithm/cards")
    public String showCards() {
        return  "/algorithm/cards";
    }

    @GetMapping("/algorithm/lookup")
    public String showAlgorithm() {
        return "/algorithm/lookup";
    }

    @GetMapping("/algorithm/registration")
    public String addAlgorithm() {
        return "/algorithm/registration";
    }

    @GetMapping("/algorithm/modification")
    public String modifyAlgorithm() {
        return "/algorithm/modification";
    }

}
