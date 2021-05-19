package diary.dao.algorithm;

import diary.dto.algorithm.FileInfo;
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
import java.util.Collections;
import java.util.Map;

import static diary.dao.sqls.FileSqls.*;

@Repository
public class FileInfoDao {
    static class FileInfoMapper implements RowMapper<FileInfo> {
        @Override
        public FileInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            return FileInfo.builder().id(rs.getInt("id"))
                    .algorithmId(rs.getInt("algorithmId"))
                    .fileName(rs.getString("fileName"))
                    .saveFileName(rs.getString("saveFileName"))
                    .contentType(rs.getString("contentType"))
                    .build();
        }
    }

    private final NamedParameterJdbcTemplate jdbc;
    private final SimpleJdbcInsert insertAction;
    private final RowMapper<FileInfo> rowMapper;

    public FileInfoDao(DataSource dataSource) {
       this.jdbc = new NamedParameterJdbcTemplate(dataSource);
       this.insertAction = new SimpleJdbcInsert(dataSource)
               .withTableName("file_info").usingGeneratedKeyColumns("id");
       this.rowMapper = BeanPropertyRowMapper.newInstance(FileInfo.class);
    }

    public FileInfo get(int id) {
        try {
            Map<String, ?> param = Collections.singletonMap("id", id);
            return jdbc.queryForObject(SELECT_FILE_INFO, param, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public FileInfo getByAlgorithmId(int algorithmId) {
        try {
            Map<String, ?> param = Collections.singletonMap("algorithmId", algorithmId);
            return jdbc.queryForObject(SELECT_FILE_INFO_BY_ALGORITHM_ID, param, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int add(FileInfo fileInfo) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(fileInfo);
        return insertAction.executeAndReturnKey(param).intValue();
    }

    public int delete(int id) {
        Map<String, ?> param = Collections.singletonMap("id", id);
        return jdbc.update(DELETE_FILE_INFO, param);
    }

    public int update(FileInfo fileInfo) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(fileInfo);
        return jdbc.update(UPDATE_FILE_INFO, params);
    }

}
