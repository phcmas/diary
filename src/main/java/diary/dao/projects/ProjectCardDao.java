package diary.dao.projects;

import diary.dto.projects.ProjectCard;
import static diary.dao.sqls.ProjectSqls.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@Repository
public class ProjectCardDao {
    static class ProjectCardRowMapper implements RowMapper<ProjectCard> {
        @Override
        public ProjectCard mapRow(ResultSet rs, int rowNum) throws SQLException {
            return ProjectCard.builder().id(rs.getInt("id"))
                    .projectId(rs.getInt("projectId")).build();
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
            return jdbc.queryForObject(SELECT_PROJECT_CARD, param, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
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

}


