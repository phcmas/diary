package diary.service.impl;

import diary.dao.projects.ProjectCardDao;
import diary.dao.projects.ProjectDao;
import diary.dao.projects.ProjectMemberDao;
import diary.dao.user.UserDao;
import diary.dto.projects.Project;
import diary.service.ProjectService;
import diary.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    UserDao userDao;

    @Autowired
    ProjectDao projectDao;

    @Autowired
    ProjectMemberDao projectMemberDao;

    @Autowired
    ProjectCardDao projectCardDao;

    private final int CARD_LIMIT = 4;

    @Override
    public int addProject(Project project) {
        int projectId = projectDao.addProject(project);
        String userName = Utility.getCurrentUserName();
        int userId = userDao.getUser(userName).getId();

        // 일단은 project와 member card가 모두 일대일 대응일때만 고려
        projectMemberDao.addProjectMember(userId, projectId);
        projectCardDao.addProjectCard(projectId);

        return projectId;
    }

    @Override
    public int updateProject(Project project) {
        return projectDao.updateProject(project);
    }

    @Override
    public Project getProject(int id) {
        return projectDao.getProject(id);
    }

    @Override
    public List<Project> getRecentProject(int start) {
        return projectDao.getRecentProjects(start, CARD_LIMIT);
    }
}



