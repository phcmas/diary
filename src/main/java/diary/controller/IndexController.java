package diary.controller;

import diary.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/main")
    public String main(Model model) {
        return "login";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        User user = new User();
        user.setId(1230);
        model.addAttribute("user", user);
        return "thymeleaf";
    }
}
