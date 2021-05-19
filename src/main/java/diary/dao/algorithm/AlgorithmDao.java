package diary.dao.algorithm;

import diary.dto.algorithm.Algorithm;
import diary.dto.enums.AlgorithmType;
import diary.dto.enums.Difficulty;
import diary.dto.enums.Language;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static diary.dao.sqls.AlgorithmSqls.*;

@Repository
public class AlgorithmDao {
    static class AlgorithmMapper implements RowMapper<Algorithm> {
        @Override
        public Algorithm mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Algorithm.builder()
                    .id(rs.getInt("id")).title(rs.getString("title"))
                    .solvedDate(Utility.convert(rs.getDate("solvedDate")))
                    .language(Language.valueOf(rs.getString("language")))
                    .type(AlgorithmType.valueOf(rs.getString("type")))
                    .source(rs.getString("source"))
                    .difficulty(Difficulty.valueOf(rs.getString("difficulty")))
                    .explanation(rs.getString("explanation"))
                    .content(rs.getString("content"))
                    .createDate(Utility.convert(rs.getDate("createDate")))
                    .modifyDate(Utility.convert(rs.getDate("modifyDate")))
                    .fileId(rs.getInt("fileId")).build();
        }
    }

    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<Algorithm> rowMapper;
    private final SimpleJdbcInsert insertAction;

    public AlgorithmDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.rowMapper = BeanPropertyRowMapper.newInstance(Algorithm.class);
        this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("algorithm")
                .usingGeneratedKeyColumns("id");
    }

    public Algorithm get(int id) {
        try {
            Map<String, ?> param = Collections.singletonMap("id", id);
            return jdbc.queryForObject(SELECT_ALGORITHM, param, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int getCount(LocalDate startDate, LocalDate endDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("startDate", startDate);
        params.put("endDate", endDate);

        return jdbc.queryForObject(SELECT_ALGORITHM_COUNT, params, Integer.class);
    }

    public List<Algorithm> getRecentAlgorithms(int start, int limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("limit", limit);

        return jdbc.query(SELECT_RECENT_ALGORITHM, params, rowMapper);
    }

    public int add(Algorithm algorithm) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(algorithm);
        return insertAction.executeAndReturnKey(params).intValue();
    }

    public int update(Algorithm algorithm) {
        BeanPropertySqlParameterSource params
                = new BeanPropertySqlParameterSource(algorithm);

        params.registerSqlType("type", Types.VARCHAR);
        params.registerSqlType("language", Types.VARCHAR);
        params.registerSqlType("difficulty", Types.VARCHAR);

        return jdbc.update(UPDATE_ALGORITHM, params);
    }

    public int delete(int id) {
        Map<String, ?> param = Collections.singletonMap("id", id);
        return jdbc.update(DELETE_ALGORITHM, param);
    }



}
