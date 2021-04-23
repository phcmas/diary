package diary.dao;

import com.mysql.cj.result.Row;
import diary.dto.UserRole;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

import static diary.dao.loginsqls.LoginSqls.*;

@Repository
public class UserRoleDao {
    static class UserRoleMapper implements RowMapper<UserRole> {
        @Override
        public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
            return UserRole.builder()
                    .id(rs.getInt("id"))
                    .userId(rs.getInt("userId"))
                    .roleName(rs.getString("roleName")).build();
        }
    }
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<UserRole> rowMapper;

    public UserRoleDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.rowMapper = BeanPropertyRowMapper.newInstance(UserRole.class);
    }

    public UserRole getUserRole(int userId) {
        try {
            Map<String, ?> param = Collections.singletonMap("user_id", userId);
            return jdbc.queryForObject(SELECT_USER_ROLE, param, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


}
