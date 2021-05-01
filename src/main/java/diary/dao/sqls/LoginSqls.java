package diary.dao.sqls;

public class LoginSqls {
    public static final String SELECT_USER = "SELECT * FROM user WHERE name = :name";
    public static final String SELECT_USER_ROLE = "SELECT * from user_role WHERE user_id = :userId";
}
