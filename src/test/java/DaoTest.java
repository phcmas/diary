import diary.config.ApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class DaoTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void DBConnectionTest() {
        Map<String, ?> param = Collections.singletonMap("","");
        NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(dataSource);

        int a = jdbc.queryForObject("SELECT count(*) FROM user", param, Integer.class);
        System.out.println(a);
    }

}
