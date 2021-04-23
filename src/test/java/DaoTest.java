import diary.config.ApplicationConfig;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
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
        Assert.assertNotNull(user);
    }

    @Test
    public void UserRoleDaoTest() {
        UserRole userRole = userRoleDao.getUserRole(1);
        Assert.assertNotNull(userRole);
    }

}
