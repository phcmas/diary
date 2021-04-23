package diary.service;

import diary.dto.User;
import org.springframework.stereotype.Service;

public interface UserService {
    public User getUser(String name);
}
