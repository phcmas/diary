package diary.controller.user;

import diary.dto.user.User;
import diary.param.UserParam;
import diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user")
public class LoginApiController {

    @Autowired
    UserService userService;

    @PostMapping(path="/")
    public int addUser(@RequestBody UserParam userParam) {
        User newUser = userParam.toUser();
        return userService.addUser(newUser);
    }

    @DeleteMapping("/{name}")
    public int deleteUser(@PathVariable(name="name") String name) {
        return 0;
    }

}
