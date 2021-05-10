package diary.dao.sqls;

public class AlgorithmSqls {
    public static String SELECT_ALGORITHM
            = "SELECT * FROM algorithm WHERE id = :id";
    public static String SELECT_ALGORITHM_CARD
            = "SELECT * FROM algorithm_card WHERE algorithm_id = :algorithmId";
    public static String SELECT_FILE_INFO
            = "SELECT * FROM file_info WHERE algorithm_id = :algorithmId";
    public static String SELECT_RECENT_ALGORITHM
            = "SELECT * FROM algorithm ORDER BY modify_date DESC limit :start, :limit";
}
