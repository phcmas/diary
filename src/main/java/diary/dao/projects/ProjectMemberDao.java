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
import java.util.*;

@Repository
public class ProjectMemberDao {
    static class ProjectMemberRowMapper implements RowMapper<ProjectMember> {
        @Override
        public ProjectMember mapRow(ResultSet rs, int rowNum) throws SQLException {
            return ProjectMember.builder().id(rs.getInt("id"))
                    .name(rs.getString("userName")).projectId(rs.getInt("projectId"))
                    .build();
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
    public List<ProjectMember> getByProjectId(int projectId) {
        Map<String, ?> param = Collections.singletonMap("projectId", projectId);
        return jdbc.query(SELECT_PROJECT_MEMBER, param, rowMapper);
    }

    @Transactional
    public int add(ProjectMember projectMember) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(projectMember);
        return insertAction.executeAndReturnKey(params).intValue();
    }

    @Transactional
    public void addBatch(List<String> names, int projectId) {
        int count = names.size();
        List<Map<String, Object>> params = new ArrayList<>(count);

        for (String name : names) {
            Map<String, Object> param = new HashMap<>();
            param.put("name", name);
            param.put("project_id", projectId);

            params.add(param);
        }

        insertAction.executeBatch(params.toArray(new Map[count]));
    }

    @Transactional
    public void addByProjectId(String name, int projectId) {
        ProjectMember newMember = new ProjectMember(name, projectId);
        add(newMember);
    }

    @Transactional
    public void deleteByProjectId(int projectId) {
        Map<String, ?> param = Collections.singletonMap("projectId", projectId);
        jdbc.update(DELETE_PROJECT_MEMBER, param);
    }

    public int[] update(List<ProjectMember> projectMembers) {
        int count = projectMembers.size();
        SqlParameterSource[] params = new SqlParameterSource[count];

        for (int i = 0; i < count; ++i) {
            params[i] = new BeanPropertySqlParameterSource(projectMembers.get(i));
        }

        return jdbc.batchUpdate(UPDATE_PROJECT_MEMBER, params);
    }
}
