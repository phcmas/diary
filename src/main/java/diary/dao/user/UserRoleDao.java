package diary.dao.user;

import diary.dto.enums.UserAuthority;
import diary.dto.user.UserRole;
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
import java.util.List;
import java.util.Map;

import static diary.dao.sqls.LoginSqls.*;

@Repository
public class UserRoleDao {
    static class UserRoleMapper implements RowMapper<UserRole> {
        @Override
        public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
            return UserRole.builder()
                    .id(rs.getInt("id"))
                    .userId(rs.getInt("userId"))
                    .name(rs.getString("name"))
                    .roleName(UserAuthority.valueOf(rs.getString("roleName"))).build();
        }
    }
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<UserRole> rowMapper;
    private final SimpleJdbcInsert insertAction;

    public UserRoleDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.rowMapper = BeanPropertyRowMapper.newInstance(UserRole.class);
        this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("user_role");
    }

    public List<UserRole> get(int userId) {
        Map<String, ?> param = Collections.singletonMap("userId", userId);
        return jdbc.query(SELECT_USER_ROLE, param, rowMapper);
    }

    public int add(UserRole userRole) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(userRole);
        return insertAction.execute(params);
    }

    public int delete(String name) {
        Map<String, ?> param = Collections.singletonMap("name", name);
        return jdbc.update(DELETE_USER_ROLE, param);
    }

}
