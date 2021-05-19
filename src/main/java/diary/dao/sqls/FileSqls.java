package diary.dao.sqls;

public class FileSqls {
    public static String SELECT_FILE_INFO
            = "SELECT * FROM file_info WHERE id = :id";
    public static String SELECT_FILE_INFO_BY_ALGORITHM_ID
            = "SELECT * FROM file_info WHERE algorithm_id = :algorithmId";

    public static String UPDATE_FILE_INFO
            = "UPDATE file_info SET algorithm_id = :algorithmId, "
            + "file_name = :fileName, save_file_name = :saveFileName, "
            + "content_type = :contentType WHERE id = :id";

    public static String DELETE_FILE_INFO
            = "DELETE FROM file_info WHERE id = :id";
    public static String DELETE_FILE_INFO_BY_ALGORITHM_ID
            = "DELETE FROM file_info WHERE algorithm_id = :algorithmId";
}
