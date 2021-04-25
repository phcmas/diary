import diary.config.ApplicationConfig;
import diary.config.SecurityConfig;
import diary.dao.UserDao;
import diary.dao.UserRoleDao;
import diary.dto.User;
import diary.dto.UserRole;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class})
public class DaoTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleDao userRoleDao;

    @Test
    public void DBConnectionTest() throws SQLException {
        Connection connection = dataSource.getConnection();
        Assert.assertNotNull(connection);
    }

    @Test
    public void UserDaoTest() {
        User user = userDao.getUser("seung");
        User newUser = User.builder()
                .password("1234").name("1232").createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now()).build();
        userDao.addUser(newUser);
        Assert.assertNotNull(user);
    }

    @Test
    public void UserRoleDaoTest() {
        User user = userDao.getUser("seung");
        List<UserRole> userRole = userRoleDao.getUserRole(user);
        Assert.assertNotNull(userRole);
    }

}
