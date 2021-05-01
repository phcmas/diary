package diary.dao.sqls;

public class ProjectSqls {
    public static final String SELECT_PROJECT_BY_ID = "SELECT * FROM project WHERE id = :id";
    public static final String SELECT_PROJECT_MEMBER = "SELECT * FROM project_member WHERE project_id = :project_id";
    public static final String SELECT_PROJECT_CARD = "SELECT * FROM project_card WHERE project_id = :project_id";
    public static final String UPDATE_PROJECT = "UPDATE project SET " +
            "title = :title, start_date = :startDate, finish_date = :finishDate, project_type = :projectType, " +
            "situation = :situation, content = :content, test_scenario = :testScenario, " +
            "create_date = :createDate, modify_date = :modifyDate WHERE id = :id";
}
