import diary.config.ApplicationConfig;
import diary.config.SecurityConfig;
import diary.dao.projects.ProjectCardDao;
import diary.dao.projects.ProjectDao;
import diary.dao.projects.ProjectMemberDao;
import diary.dao.user.UserDao;
import diary.dao.user.UserRoleDao;
import diary.dto.enums.UserAuthority;
import diary.dto.projects.Project;
import diary.dto.projects.ProjectCard;
import diary.dto.projects.ProjectMember;
import diary.dto.enums.ProjectType;
import diary.dto.user.User;
import diary.dto.user.UserRole;
import diary.utility.Utility;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class})
public class ProjectDaoTest {
    @Autowired
    DataSource dataSource;

    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleDao userRoleDao;

    @Autowired
    ProjectDao projectDao;

    @Autowired
    ProjectMemberDao projectMemberDao;

    @Autowired
    ProjectCardDao projectCardDao;

    public int getProjectId() {
        List<Project> recentProjects = projectDao.getRecentList(0,4);
        return recentProjects.get(0).getId();
    }

    public int getUserId() {
        User user = userDao.getUser("test");
        return user.getId();
    }

    @Test
    public void DaoTest0_DBConnectionTest() throws SQLException {
        Connection connection = dataSource.getConnection();
        Assert.assertNotNull(connection);
    }

    @Test
    public void DaoTest1_User() {
        // add newUser
        User newUser = User.builder()
                .password("1234").name("test").createDate(LocalDate.now())
                .modifyDate(LocalDate.now()).build();
        int newId = userDao.addUser(newUser);
        Assert.assertTrue(newId >= 0);

        // find user
        User user = userDao.getUser("test");
        Assert.assertNotNull(user);
    }

    @Test
    public void DaoTest2_UserRole() {
        int userId = getUserId();
        // add userRole
        UserRole userRole = new UserRole(userId, UserAuthority.USER);
        int newId = userRoleDao.addUserRole(userRole);
        Assert.assertTrue(newId >= 0);

        // find userRole
        List<UserRole> userRoles = userRoleDao.getUserRole(userId);
        Assert.assertNotEquals(userRoles.size(), 0);
    }

    @Test
    public void DaoTest3_Project() {
        // add newProject
        Project newProject = Project.builder().title("test").startDate(LocalDate.now())
                .endDate(LocalDate.now()).projectType(ProjectType.ERROR_RESOLUTION)
                .content("test").testScenario("test").createDate(LocalDate.now())
                .modifyDate(LocalDate.now()).build();
        int newId = projectDao.add(newProject);
        Assert.assertTrue(newId >= 0);

        // find project
        Project project = projectDao.get(newId);
        Assert.assertNotNull(project);
    }

    @Test
    public void DaoTest4_ProjectMember() {
        int projectId = getProjectId();
        int userId = getUserId();

        // add newProjectMember
        ProjectMember newProjectMember = ProjectMember.builder().name("test")
                .projectId(projectId).build();
        int newId = projectMemberDao.add(newProjectMember);
        Assert.assertNotEquals(newId, 0);

        // find projectMembers
        List<ProjectMember> projectMembers = projectMemberDao.getByProjectId(projectId);
        Assert.assertNotEquals(projectMembers.size(), 0);
    }

    @Test
    public void DaoTest5_ProjectCard() {
        int projectId = getProjectId();

        // add newProjectCard
        ProjectCard newProjectCard = ProjectCard.builder().projectId(projectId)
                .projectType(ProjectType.ERROR_RESOLUTION).shortTitle("test").shortContent("test")
                .memberCount(1).startDate(LocalDate.now()).build();
        int newId = projectCardDao.add(newProjectCard);
        Assert.assertNotEquals(newId, 0);

        // find projectCard
        ProjectCard projectCard = projectCardDao.getByProjectId(projectId);
        Assert.assertNotNull(projectCard);
    }

    @Test
    public void DaoTest6_ProjectUpdate() {
        int projectId = getProjectId();

        // update project
        Project modifiedProject = Project.builder().id(projectId).title("test_updated").startDate(LocalDate.now())
                .endDate(LocalDate.now()).projectType(ProjectType.FUNCTION_DEVELOPMENT)
                .content("test_updated").testScenario("test_updated").createDate(LocalDate.now())
                .modifyDate(LocalDate.now()).build();
        int result = projectDao.update(modifiedProject);
        Assert.assertNotEquals(result, 0);
    }

    @Test
    public void DaoTest7_ProjectCards() throws ParseException {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusYears(1);
        //Date startDate = Utility.getDate("2021","1","1");
        //Date endDate = Utility.addTime(startDate, 1, 0, 0);

        List<ProjectCard> projectCards = projectCardDao.getList(0,4, startDate, endDate);

        Assert.assertNotEquals(projectCards.size(), 0);
    }

    @Test
    public void DaoTest8_ProjectMemberBatchInsert() {
        int projectId = getProjectId();

        String name1 = "TEST1";
        String name2 = "TEST2";
        String name3 = "TEST3";

        List<String> names = new ArrayList<>();
        names.add(name1);
        names.add(name2);
        names.add(name3);

        projectMemberDao.addBatch(names, 1);
    }

    @Test
    public void DaoTest9_ProjectDelete() {
        int projectId = getProjectId();

        projectMemberDao.deleteByProjectId(projectId);
        projectCardDao.deleteByProjectId(projectId);
        projectDao.delete(projectId);
    }


}


