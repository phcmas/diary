package diary.dao.loginsqls;

public class LoginSqls {
    public static final String SELECT_USER = "SELECT * FROM user WHERE name = :name";
    public static final String SELECT_USER_ROLE = "SELECT * from user_role WHERE user_id = :user_id";
    public static final String INSERT_USER
            = "INSERT INTO user(name, password, create_date, modify_date)"
              +" VALUES (:name, :password, :create_date, :modify_date)";
}
