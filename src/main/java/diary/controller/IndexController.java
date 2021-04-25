package diary.controller;

import diary.dto.User;
import diary.utility.Utility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /** Login 관련 **/
    @GetMapping(path="/signin")
    public String signin() {
        return "signin";
    }

    @GetMapping(path="/signup")
    public String signup() {
        return "signup";
    }

    /** main 페이지 관련 **/
    @GetMapping("/main")
    public String main(Model model) {
        String username = Utility.getCurrentUserName();
        model.addAttribute("name", username);
        return "main";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    /** Test (삭제 예정) **/
    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        User user = new User();
        user.setId(1230);
        model.addAttribute("user", user);
        return "thymeleaf";
    }

}
