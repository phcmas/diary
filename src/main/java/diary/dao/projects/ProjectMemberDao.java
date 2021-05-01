package diary.dao.projects;

import diary.dto.projects.ProjectMember;
import static diary.dao.sqls.ProjectSqls.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProjectMemberDao {
    static class ProjectMemberRowMapper implements RowMapper<ProjectMember> {
        @Override
        public ProjectMember mapRow(ResultSet rs, int rowNum) throws SQLException {
            return ProjectMember.builder().id(rs.getInt("id")).userId(rs.getInt("userId"))
                    .projectId(rs.getInt("projectId")).build();
        }
    }

    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<ProjectMember> rowMapper;
    private final SimpleJdbcInsert insertAction;

    public ProjectMemberDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.rowMapper = BeanPropertyRowMapper.newInstance(ProjectMember.class);
        this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("project_member")
                .usingGeneratedKeyColumns("id");
    }

    @Transactional
    public List<ProjectMember> getProjectMembers(int projectId) {
        Map<String, ?> param = Collections.singletonMap("projectId", projectId);
        return jdbc.query(SELECT_PROJECT_MEMBER, param, rowMapper);
    }

    @Transactional
    public int addProjectMember(ProjectMember projectMember) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(projectMember);
        return insertAction.executeAndReturnKey(params).intValue();
    }

    @Transactional
    public int addProjectMember(int userId, int projectId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("projectId", projectId);

        return insertAction.executeAndReturnKey(params).intValue();
    }


}
