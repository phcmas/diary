package diary.dao.projects;

import diary.dto.enums.ProjectType;
import diary.dto.projects.Project;
import diary.utility.Utility;
import org.springframework.dao.EmptyResultDataAccessException;
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
import java.sql.Types;
import java.util.*;

import static diary.dao.sqls.ProjectSqls.*;

@Repository
public class ProjectDao {
    static class ProjectMapper implements RowMapper<Project> {
        @Override
        public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Project.builder()
                    .id(rs.getInt("id"))
                    .title((rs.getString("title"))).startDate(Utility.convert(rs.getDate("startDate")))
                    .endDate(Utility.convert(rs.getDate("finishDate")))
                    .projectType(ProjectType.valueOf(rs.getString("projectType")))
                    .situation(rs.getString("situation"))
                    .content(rs.getString("content"))
                    .testScenario(rs.getString("testScenario"))
                    .createDate(Utility.convert(rs.getDate("createDate")))
                    .modifyDate(Utility.convert(rs.getDate("modifyDate")))
                    .build();
        }
    }

    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<Project> rowMapper;
    private final SimpleJdbcInsert insertAction;

    public ProjectDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.rowMapper = BeanPropertyRowMapper.newInstance(Project.class);
        this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("project")
                                .usingGeneratedKeyColumns("id");
    }

    @Transactional
    public Project getProject(int id) {
        try {
            Map<String, ?> param = Collections.singletonMap("id", id);
            return jdbc.queryForObject(SELECT_PROJECT_BY_ID, param, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Transactional
    public int getProjectCount(Date startDate, Date endDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("startDate", startDate);
        params.put("endDate", endDate);

        return jdbc.queryForObject(SELECT_PROJECT_COUNT, params, Integer.class);
    }

    @Transactional
    public List<Project> getRecentProjects(int start, int limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("limit", limit);

        return jdbc.query(SELECT_RECENT_PROJECTS, params, rowMapper);
    }

    @Transactional
    public int addProject(Project project) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(project);
        return insertAction.executeAndReturnKey(params).intValue();
    }

    @Transactional
    public int updateProject(Project project) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(project);
        params.registerSqlType("projectType", Types.VARCHAR);
        return jdbc.update(UPDATE_PROJECT, params);
    }

    @Transactional
    public int deleteProject(int id) {
        Map<String, ?> param = Collections.singletonMap("id", id);
        return jdbc.update(DELETE_PROJECT, param);
    }

}


