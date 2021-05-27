package diary.dao.projects;

import diary.dto.enums.ProjectType;
import diary.dto.projects.ProjectCard;
import static diary.dao.sqls.ProjectSqls.*;

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
import java.sql.Types;
import java.time.LocalDate;
import java.util.*;

@Repository
public class ProjectCardDao {
    static class ProjectCardRowMapper implements RowMapper<ProjectCard> {
        @Override
        public ProjectCard mapRow(ResultSet rs, int rowNum) throws SQLException {
            return ProjectCard.builder().id(rs.getInt("id"))
                    .projectId(rs.getInt("projectId"))
                    .projectType(ProjectType.valueOf(rs.getString("projectType")))
                    .shortTitle(rs.getString("shortTitle"))
                    .shortContent(rs.getString("shortContent"))
                    .memberCount(rs.getInt("memberCount"))
                    .startDate(Utility.convert(rs.getDate("startDate")))
                    .endDate(Utility.convert(rs.getDate("endDate")))
                    .build();
        }
    }

    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<ProjectCard> rowMapper;
    private final SimpleJdbcInsert insertAction;

    public ProjectCardDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.rowMapper = BeanPropertyRowMapper.newInstance(ProjectCard.class);
        this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("project_card")
                                .usingGeneratedKeyColumns("id");
    }

    public ProjectCard getByProjectId(int projectId) {
        try {
            Map<String, ?> param = Collections.singletonMap("projectId", projectId);
            return jdbc.queryForObject(SELECT_PROJECT_CARD_BY_PROJECT_ID, param, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<ProjectCard> getList(int start, int limit, LocalDate fromDate, LocalDate toDate) {
        Map<String, Object> param = new HashMap<>();

        param.put("fromDate", fromDate);
        param.put("toDate", toDate);
        param.put("start", start);
        param.put("limit", limit);
        return jdbc.query(SELECT_PROJECT_CARD_BY_DATE, param, rowMapper);
    }

    public int add(ProjectCard projectCard) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(projectCard);
        return insertAction.executeAndReturnKey(params).intValue();
    }

    public void update(ProjectCard projectCard) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(projectCard);
        params.registerSqlType("projectType", Types.VARCHAR);
        jdbc.update(UPDATE_PROJECT_CARD, params);
    }

    public void deleteByProjectId(int projectId) {
        Map<String, ?> param = Collections.singletonMap("projectId", projectId);
        jdbc.update(DELETE_PROJECT_CARD, param);
    }


}


