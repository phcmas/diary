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
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class})
public class DaoTest {

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
        List<Project> recentProjects = projectDao.getRecentProjects(0,4);
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
    public void DaoTest1_UserTest1() {
        // add newUser
        User newUser = User.builder()
                .password("1234").name("test").createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now()).build();
        int newId = userDao.addUser(newUser);
        Assert.assertTrue(newId >= 0);

        // find user
        User user = userDao.getUser("test");
        Assert.assertNotNull(user);
    }

    @Test
    public void DaoTest2_UserTest2() {
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
    public void DaoTest3_ProjectTest1() {
        // add newProject
        Project newProject = Project.builder().title("test").startDate(LocalDateTime.now())
                .finishDate(LocalDateTime.now()).projectType(ProjectType.ErrorResolution)
                .situation("test").content("test").testScenario("test").createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now()).build();
        int newId = projectDao.addProject(newProject);
        Assert.assertTrue(newId >= 0);

        // find project
        Project project = projectDao.getProject(newId);
        Assert.assertNotNull(project);
    }

    @Test
    public void DaoTest4_ProjectTest2() {
        int projectId = getProjectId();
        int userId = getUserId();

        // add newProjectMember
        ProjectMember newProjectMember = ProjectMember.builder().userId(userId).projectId(projectId).build();
        int newId = projectMemberDao.addProjectMember(newProjectMember);
        Assert.assertNotEquals(newId, 0);

        // find projectMembers
        List<ProjectMember> projectMembers = projectMemberDao.getProjectMembers(projectId);
        Assert.assertNotEquals(projectMembers.size(), 0);
    }

    @Test
    public void DaoTest5_ProjectTest3() {
        int projectId = getProjectId();

        // add newProjectCard
        ProjectCard newProjectCard = ProjectCard.builder().projectId(projectId).build();
        int newId = projectCardDao.addProjectCard(newProjectCard);
        Assert.assertNotEquals(newId, 0);

        // find projectCard
        ProjectCard projectCard = projectCardDao.getProjectCard(projectId);
        Assert.assertNotNull(projectCard);
    }

    @Test
    public void DaoTest6_ProjectTest4() {
        int projectId = getProjectId();

        // update project
        Project modifiedProject = Project.builder().id(projectId).title("test_updated").startDate(LocalDateTime.now())
                .finishDate(LocalDateTime.now()).projectType(ProjectType.FunctionDevelopment)
                .situation("test_updated").content("test_updated").testScenario("test_updated").createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now()).build();
        int result = projectDao.updateProject(modifiedProject);
        Assert.assertNotEquals(result, 0);
    }

}


