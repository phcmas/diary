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

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    public List<AlgorithmCard> getByAlgorithmId(int algorithmId) {
        Map<String, ?> param = Collections.singletonMap("algorithmId", algorithmId);
        return jdbc.query(SELECT_ALGORITHM_CARD, param, rowMapper);
    }

    public int add(AlgorithmCard card) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(card);
        return insertAction.executeAndReturnKey(params).intValue();
    }

    public int update(AlgorithmCard card) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(card);
        params.registerSqlType("type", Types.VARCHAR);
        params.registerSqlType("difficulty", Types.VARCHAR);
        return jdbc.update(UPDATE_ALGORITHM_CARD, params);
    }


}


