package diary.dao.algorithm;

import diary.dto.algorithm.FileInfo;
import diary.dto.enums.Language;
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
import java.util.List;
import java.util.Map;

import static diary.dao.sqls.AlgorithmSqls.*;

@Repository
public class FileInfoDao {
    static class FileInfoMapper implements RowMapper<FileInfo> {
        @Override
        public FileInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            return FileInfo.builder().id(rs.getInt("id"))
                    .fileName(rs.getString("fileName"))
                    .language(Language.valueOf(rs.getString("language")))
                    .algorithmId(rs.getInt("algorithmId"))
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

    public List<FileInfo> getByAlgorithmId(int algorithmId) {
        Map<String, ?> param = Collections.singletonMap("algorithmId", algorithmId);
        return jdbc.query(SELECT_FILE_INFO, param, rowMapper);
    }

    @Transactional
    public int add(FileInfo fileInfo) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(fileInfo);
        return insertAction.executeAndReturnKey(param).intValue();
    }


}
