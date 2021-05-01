package diary.controller;

import diary.dto.user.User;
import diary.param.UserParam;
import diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/user")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/join")
    @ResponseBody
    public int join(@RequestBody UserParam userParam) {
        User newUser = userParam.toUser();
        return userService.addUser(newUser);
    }

}
