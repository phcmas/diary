package diary.controller;

import diary.utility.Utility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("name", Utility.getCurrentUserName());
        return "main";
    }

    @GetMapping("/cs")
    public String cs() {
        return "cs";
    }


    /** Test (삭제 예정 ) **/
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

}
