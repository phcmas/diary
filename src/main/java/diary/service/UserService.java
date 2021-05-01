package diary.service;

import diary.dto.user.User;
import diary.dto.user.UserRole;

import java.util.List;

public interface UserService {
    public User getUser(String name);
    public List<UserRole> getUserRole(User user);
    public int addUser(User user);
}
