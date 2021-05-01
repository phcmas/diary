package diary.service.impl;

import diary.dao.user.UserDao;
import diary.dao.user.UserRoleDao;
import diary.dto.user.User;
import diary.dto.user.UserRole;
import diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleDao userRoleDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User getUser(String name) {
        return userDao.getUser(name);
    }

    @Override
    public List<UserRole> getUserRole(User user) {
        return userRoleDao.getUserRole(user);
    }

    @Override
    public int addUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userDao.addUser(user);
    }
}


