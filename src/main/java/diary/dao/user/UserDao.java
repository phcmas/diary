package diary.dao.user;

import diary.dto.user.User;
import diary.utility.Utility;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

import static diary.dao.sqls.LoginSqls.*;

@Repository
public class UserDao {
    static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return User.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .password(rs.getString("password"))
                    .createDate(Utility.convert(rs.getDate("createDate")))
                    .modifyDate(Utility.convert(rs.getDate("modifyDate"))).build();
        }
    }

    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<User> rowMapper;
    private final SimpleJdbcInsert insertAction;

    public UserDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.rowMapper = BeanPropertyRowMapper.newInstance(User.class);
        this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("user")
                                .usingGeneratedKeyColumns("id");
    }

    public User get(String name) {
        try {
            Map<String, ?> param = Collections.singletonMap("name", name);
            return jdbc.queryForObject(SELECT_USER, param, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int add(User user) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        return insertAction.executeAndReturnKey(params).intValue();
    }

    public int delete(String name) {
        Map<String, ?> param = Collections.singletonMap("name", name);
        return jdbc.update(DELETE_USER, param);
    }



}
