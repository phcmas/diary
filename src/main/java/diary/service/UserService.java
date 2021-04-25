package diary.service;

import diary.dto.User;
import diary.dto.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public User getUser(String name);
    public List<UserRole> getUserRole(User user);
    public int addUser(User user);
}
