package diary.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/user")
public class LoginController {

    @GetMapping("/signin")
    public String signin() {
        return "/user/signin";
    }

    @GetMapping("/signup")
    public String signup() {
        return "/user/signup";
    }

    @GetMapping("/withdrawal")
    public String withdrawal() {
        return "/user/withdrawal";
    }

}
