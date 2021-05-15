package diary.dao.algorithm;

import diary.dto.algorithm.AlgorithmCard;
import diary.dto.enums.AlgorithmType;
import diary.dto.enums.Difficulty;
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
import java.time.LocalDate;
import java.util.*;

import static diary.dao.sqls.AlgorithmSqls.*;

@Repository
public class AlgorithmCardDao {
    static class AlgorithmCardMapper implements RowMapper<AlgorithmCard> {
        @Override
        public AlgorithmCard mapRow(ResultSet rs, int rowNum) throws SQLException {
            return AlgorithmCard.builder().id(rs.getInt("id"))
                    .algorithmId(rs.getInt("algorithmId"))
                    .shortTitle(rs.getString("shortTitle"))
                    .shortExplanation(rs.getString("shortExplanation"))
                    .type(AlgorithmType.valueOf(rs.getString("type")))
                    .difficulty(Difficulty.valueOf(rs.getString("difficulty")))
                    .solvedDate(Utility.convert(rs.getDate("solvedDate")))
                    .build();
        }
    }

    private final NamedParameterJdbcTemplate jdbc;
    private final SimpleJdbcInsert insertAction;
    private final RowMapper<AlgorithmCard> rowMapper;

    public AlgorithmCardDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("algorithm_card")
                .usingGeneratedKeyColumns("id");
        this.rowMapper = BeanPropertyRowMapper.newInstance(AlgorithmCard.class);
    }

    public AlgorithmCard getByAlgorithmId(int algorithmId) {
        try {
            Map<String, ?> param = Collections.singletonMap("algorithmId", algorithmId);
            return jdbc.queryForObject(SELECT_ALGORITHM_CARD, param, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<AlgorithmCard> getList(int start, int limit, LocalDate startDate, LocalDate endDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("limit", limit);
        params.put("startDate", startDate);
        params.put("endDate", endDate);

        return jdbc.query(SELECT_ALGORITHM_CARD_BY_DATE, params, rowMapper);
    }

    @Transactional
    public int add(AlgorithmCard card) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(card);
        return insertAction.executeAndReturnKey(params).intValue();
    }

    @Transactional
    public int update(AlgorithmCard card) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(card);
        params.registerSqlType("type", Types.VARCHAR);
        params.registerSqlType("difficulty", Types.VARCHAR);

        return jdbc.update(UPDATE_ALGORITHM_CARD, params);
    }

    @Transactional
    public int deleteByAlgorithmId(int algorithmId) {
        Map<String, ?> param = Collections.singletonMap("algorithmId", algorithmId);
        return jdbc.update(DELETE_ALGORITHM_CARD, param);
    }


}


