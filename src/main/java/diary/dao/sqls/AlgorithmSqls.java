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
    public static String SELECT_ALGORITHM_CARD_BY_DATE
            = "SELECT * FROM algorithm_card WHERE solved_date >= :startDate "
            + "AND solved_date < :endDate ORDER BY solved_date DESC LIMIT :start, :limit";

    public static String UPDATE_ALGORITHM_CARD
            = "UPDATE algorithm_card SET algorithm_id = :algorithmId, "
            + "short_title = :shortTitle, short_explanation = :shortExplanation, "
            + "type = :type, difficulty = :difficulty, solved_date = :solvedDate "
            + "WHERE id = :id";

    public static String UPDATE_ALGORITHM
            = "UPDATE algorithm SET title = :title, solved_date = :solvedDate, "
            + "language = :language, type = :type, source = :source, "
            + "difficulty = :difficulty, explanation = :explanation, "
            + "content = :content, create_date = :createDate, modify_date = :modifyDate, "
            + "file_id = :fileId WHERE id = :id";


    public static String DELETE_ALGORITHM
            = "DELETE FROM algorithm WHERE id = :id";
    public static String DELETE_ALGORITHM_CARD
            = "DELETE FROM algorithm_card WHERE algorithm_id = :algorithmId";
}
