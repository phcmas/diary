import diary.config.ApplicationConfig;
import diary.config.SecurityConfig;
import diary.dao.projects.ProjectCardDao;
import diary.dao.projects.ProjectDao;
import diary.dao.projects.ProjectMemberDao;
import diary.dao.user.UserDao;
import diary.dao.user.UserRoleDao;
import diary.dto.projects.Project;
import diary.dto.projects.ProjectCard;
import diary.dto.projects.ProjectMember;
import diary.dto.enums.ProjectType;
import diary.dto.user.User;
import diary.dto.user.UserRole;
import org.junit.Assert;
import org.junit.Before;
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

    @Test
    public void DaoTest0_DBConnectionTest() throws SQLException {
        Connection connection = dataSource.getConnection();
        Assert.assertNotNull(connection);
    }

    @Test
    public void DaoTest1_UserTest1() {
        // add newUser
        User newUser = User.builder().id(-1)
                .password("1234").name("test").createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now()).build();
        int result = userDao.addUser(newUser);
        Assert.assertNotEquals(result, 0);

        // find user
        User user = userDao.getUser("test");
        Assert.assertNotNull(user);
    }

    @Test
    public void DaoTest2_UserTest2() {
        // find userRole
        User user = userDao.getUser("test");
        List<UserRole> userRole = userRoleDao.getUserRole(user);
        Assert.assertNotEquals(userRole.size(), 0);
    }

    @Test
    public void DaoTest3_ProjectTest1() {
        // add newProject
        Project newProject = Project.builder().id(-1).title("test").startDate(LocalDateTime.now())
                .finishDate(LocalDateTime.now()).projectType(ProjectType.ErrorResolution)
                .situation("test").content("test").testScenario("test").createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now()).build();
        int result1 = projectDao.addProject(newProject);
        Assert.assertNotEquals(result1, 0);

        // find project
        Project project = projectDao.getProject(-1);
        Assert.assertNotNull(project);
    }

    @Test
    public void DaoTest4_ProjectTest2() {
        // add newProjectMember
        ProjectMember newProjectMember = ProjectMember.builder().userId(-1).projectId(-1).build();
        int result2 = projectMemberDao.addProjectMember(newProjectMember);
        Assert.assertNotEquals(result2, 0);

        // find projectMembers
        List<ProjectMember> projectMembers = projectMemberDao.getProjectMembers(-1);
        Assert.assertNotEquals(projectMembers.size(), 0);
    }

    @Test
    public void DaoTest5_ProjectTest3() {
        // add newProjectCard
        ProjectCard newProjectCard = ProjectCard.builder().id(-1).projectId(-1).build();
        int result3 = projectCardDao.addProjectCard(newProjectCard);
        Assert.assertNotEquals(result3, 0);

        // find projectCard
        ProjectCard projectCard = projectCardDao.getProjectCard(-1);
        Assert.assertNotNull(projectCard);
    }

    @Test
    public void DaoTest6_ProjectTest4() {
        // update project
        Project modifiedProject = Project.builder().id(-1).title("test_updated").startDate(LocalDateTime.now())
                .finishDate(LocalDateTime.now()).projectType(ProjectType.FunctionDevelopment)
                .situation("test_updated").content("test_updated").testScenario("test_updated").createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now()).build();
        int result4 = projectDao.updateProject(modifiedProject);
        Assert.assertNotEquals(result4, 0);
    }

}


