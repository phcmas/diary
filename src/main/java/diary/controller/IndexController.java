package diary.controller;

import diary.dto.User;
import diary.utility.Utility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /** Login **/
    @GetMapping(path="/user/signin")
    public String signin() {
        return "/user/signin";
    }

    @GetMapping(path="/user/signup")
    public String signup() {
        return "/user/signup";
    }

    /** Main Page**/
    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("name", Utility.getCurrentUserName());
        return "main";
    }

    /** History **/
    @GetMapping("/history/cards")
    public String history() {
        return "/history/cards";
    }

    @GetMapping("/history/register")
    public String register() {
        return "/history/register";
    }

    @GetMapping("/history/modify")
    public String modify() {
        return "/history/modify";
    }

    @GetMapping("/history/lookup")
    public String lookup() {
        return "/history/lookup";
    }

    /** Algorithm **/
    @GetMapping("/algorithm/cards")
    public String algorithm() {
        return "/algorithm/cards";
    }

    @GetMapping("/algorithm/lookup")
    public String alookup() {
        return "/algorithm/lookup";
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
