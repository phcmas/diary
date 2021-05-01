package diary.controller;

import diary.utility.Utility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @GetMapping("/projects/cards")
    public String projects() {
        return "/projects/cards";
    }

    @GetMapping("/projects/register")
    public String register() {
        return "/projects/register";
    }

    @GetMapping("/projects/modify")
    public String modify() {
        return "/projects/modify";
    }

    @GetMapping("/projects/lookup")
    public String lookup() {
        return "/projects/lookup";
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

    @GetMapping("/algorithm/register")
    public String aregister() {
        return "/algorithm/register";
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
