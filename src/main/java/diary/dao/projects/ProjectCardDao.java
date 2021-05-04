package diary.dao.projects;

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
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class ProjectCardDao {
    static class ProjectCardRowMapper implements RowMapper<ProjectCard> {
        @Override
        public ProjectCard mapRow(ResultSet rs, int rowNum) throws SQLException {
            return ProjectCard.builder().id(rs.getInt("id"))
                    .projectId(rs.getInt("projectId"))
                    .projectType(rs.getString("projectType"))
                    .shortTitle(rs.getString("shortTitle"))
                    .shortContent(rs.getString("shortContent"))
                    .memberCount(rs.getInt("memberCount"))
                    .startDate(Utility.convert(rs.getDate("startDate")))
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

    public ProjectCard getProjectCard(int projectId) {
        try {
            Map<String, ?> param = Collections.singletonMap("projectId", projectId);
            return jdbc.queryForObject(SELECT_PROJECT_CARD_BY_PROJECT_ID, param, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<ProjectCard> getProjectCards(int start, int limit, Date startDate, Date endDate) {
        Map<String, Object> param = new HashMap<>();

        param.put("startDate", startDate);
        param.put("endDate", endDate);
        param.put("start", start);
        param.put("limit", limit);
        return jdbc.query(SELECT_PROJECT_CARD_BY_DATE, param, rowMapper);
    }

    @Transactional
    public int addProjectCard(ProjectCard projectCard) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(projectCard);
        return insertAction.executeAndReturnKey(params).intValue();
    }

    @Transactional
    public int addProjectCard(int projectId) {
        ProjectCard newCard = new ProjectCard(projectId);
        return addProjectCard(newCard);
    }

    @Transactional
    public int updateProjectCard(ProjectCard projectCard) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(projectCard);
        return jdbc.update(UPDATE_PROJECT_CARD, params);
    }

    @Transactional
    public int deleteProjectCard(int projectId) {
        Map<String, ?> param = Collections.singletonMap("projectId", projectId);
        return jdbc.update(DELETE_PROJECT_CARD, param);
    }


}


