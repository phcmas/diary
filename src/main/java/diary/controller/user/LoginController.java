package diary.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/user")
public class LoginController {

    @GetMapping(path="/signin")
    public String signin() {
        return "/user/signin";
    }

    @GetMapping(path="/signup")
    public String signup() {
        return "/user/signup";
    }

}
