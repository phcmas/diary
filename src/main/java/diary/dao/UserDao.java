package diary.dao;

import diary.dto.User;
import diary.utility.Utility;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

import static diary.dao.loginsqls.LoginSqls.*;

@Repository
public class UserDao {
    static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return User.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .password(rs.getString("password"))
                    .createDate(Utility.convertDate(rs.getDate("createDate")))
                    .modifyDate(Utility.convertDate(rs.getDate("modifyDate"))).build();
        }
    }

    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<User> rowMapper;

    public UserDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.rowMapper = BeanPropertyRowMapper.newInstance(User.class);
    }


    @Transactional
    public User getUser(String name) {
        try {
            Map<String, ?> param = Collections.singletonMap("name", name);
            return jdbc.queryForObject(SELECT_USER, param, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
