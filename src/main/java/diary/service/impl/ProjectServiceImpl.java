package diary.service.impl;

import diary.dao.projects.ProjectCardDao;
import diary.dao.projects.ProjectDao;
import diary.dao.projects.ProjectMemberDao;
import diary.dto.projects.Project;
import diary.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectDao projectDao;

    @Autowired
    ProjectMemberDao projectMemberDao;

    @Autowired
    ProjectCardDao projectCardDao;

    @Override
    public int addProject(Project project) {
        return projectDao.addProject(project);
    }

    @Override
    public int updateProject(Project project) {
        return projectDao.updateProject(project);
    }

    @Override
    public Project getProject(int id) {
        return null;
    }

    @Override
    public List<Project> getRecentProject() {
        return null;
    }
}



