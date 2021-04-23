package diary.service.impl;

import diary.dao.UserDao;
import diary.dto.User;
import diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User getUser(String name) {
        return userDao.getUser(name);
    }

}
