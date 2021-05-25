package diary.service.impl;

import diary.dao.user.UserDao;
import diary.dao.user.UserRoleDao;
import diary.dto.enums.UserAuthority;
import diary.dto.user.User;
import diary.dto.user.UserRole;
import diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return userDao.get(name);
    }

    @Override
    public List<UserRole> getUserRole(int userId) {
        return userRoleDao.get(userId);
    }

    @Override
    @Transactional
    public int addUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        int newId = userDao.add(user);

        UserRole newUserRole = UserRole.builder()
                .userId(newId).name(user.getName()).roleName(UserAuthority.USER)
                .build();
        userRoleDao.add(newUserRole);

        return newId;
    }

    @Override
    @Transactional
    public int delete(String name) {
        userRoleDao.delete(name);
        return userDao.delete(name);
    }

}

