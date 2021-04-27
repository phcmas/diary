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

    /** 페이지 관련 **/
    @GetMapping("/main")
    public String main(Model model) {
        String username = Utility.getCurrentUserName();
        model.addAttribute("name", username);
        return "main";
    }

    @GetMapping("/history")
    public String history() {
        return "history";
    }

    @GetMapping("/algorithm")
    public String algorithm() {
        return "algorithm";
    }

    @GetMapping("/cs")
    public String cs() {
        return "cs";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/modify")
    public String modify() {
        return "modify";
    }

    @GetMapping("/lookup")
    public String lookup() {
        return "lookup";
    }

    /** Test (삭제 예정 ) **/
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

}
