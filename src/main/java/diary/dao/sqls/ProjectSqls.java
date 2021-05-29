package diary.dao.sqls;

public class ProjectSqls {
    public static final String SELECT_PROJECT_BY_ID
            = "SELECT * FROM project WHERE id = :id";
    public static final String SELECT_PROJECT_MEMBER
            = "SELECT * FROM project_member WHERE project_id = :projectId";
    public static final String SELECT_AUTHOR
            = "SELECT author FROM project WHERE id = :id";
    public static final String SELECT_PROJECT_CARD_BY_PROJECT_ID
            = "SELECT * FROM project_card WHERE project_id = :projectId";
    public static final String SELECT_PROJECT_CARD_BY_DATE
            = "SELECT * FROM project_card WHERE"
            + " start_date >= :fromDate AND start_date < :toDate ORDER BY start_date DESC LIMIT :start, :limit";
    public static final String SELECT_RECENT_PROJECTS
            = "SELECT * FROM project ORDER BY modify_date DESC limit :start, :limit";
    public static final String SELECT_PROJECT_COUNT
            = "SELECT count(*) FROM project WHERE start_date >= :fromDate AND start_date < :toDate";

    public static final String UPDATE_PROJECT
            = "UPDATE project SET title = :title, start_date = :startDate, "
            + "end_date = :endDate, project_type = :projectType, "
            + "situation = :situation, content = :content, test_scenario = :testScenario, "
            + "create_date = :createDate, modify_date = :modifyDate WHERE id = :id";
    public static final String UPDATE_PROJECT_CARD
            = "UPDATE project_card SET project_type = :projectType, "
            + "short_title = :shortTitle, short_content = :shortContent, "
            + "member_count = :memberCount, start_date = :startDate WHERE id = :id";
    public static final String UPDATE_PROJECT_MEMBER
            = "UPDATE project_member SET name = :name WHERE project_id = :projectId";

    public static final String DELETE_PROJECT
            = "DELETE FROM project WHERE id = :id";
    public static final String DELETE_PROJECT_CARD
            = "DELETE FROM project_card WHERE project_id = :projectId";
    public static final String DELETE_PROJECT_MEMBER
            = "DELETE FROM project_member WHERE project_id = :projectId";
}
