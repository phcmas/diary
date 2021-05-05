package diary.service.impl;

import diary.dao.projects.ProjectCardDao;
import diary.dao.projects.ProjectDao;
import diary.dao.projects.ProjectMemberDao;
import diary.dao.user.UserDao;
import diary.dto.projects.Project;
import diary.dto.projects.ProjectCard;
import diary.service.ProjectService;
import diary.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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
    public ProjectCard makeProjectCard(Project project, int projectId, int memberCount) {
        ProjectCard projectCard = ProjectCard.builder()
                .projectId(projectId).projectType(project.getProjectType().getStringFormat())
                .memberCount(1).startDate(project.getStartDate()).build();

        String shortTitle = Utility.cutString(project.getTitle(), 10);
        String shortContent = Utility.cutString(project.getContent(), 50);
        projectCard.setShortTitle(shortTitle);
        projectCard.setShortContent(shortContent);

        return projectCard;
    }

    @Override
    @Transactional
    public int addProject(Project project, List<String> members) {
        int projectId = projectDao.addProject(project);

        // 일단은 project와 member card가 모두 일대일 대응일때만 고려
        String userName = Utility.getCurrentUserName();
        int userId = userDao.getUser(userName).getId();
        projectMemberDao.addProjectMember(userId, userName, projectId);

        ProjectCard projectCard = makeProjectCard(project, projectId, 1);
        projectCardDao.addProjectCard(projectCard);

        return projectId;
    }

    @Override
    public int updateProject(Project project) {
        int projectId = project.getId();
        int projectCardId = projectCardDao.getProjectCard(projectId).getId();

        ProjectCard projectCard = makeProjectCard(project, projectId, 1);
        projectCard.setId(projectCardId);
        projectCardDao.updateProjectCard(projectCard);

        return projectDao.updateProject(project);
    }

    @Override
    public int deleteProject(int id) {
        projectMemberDao.deleteProjectMember(id);
        projectCardDao.deleteProjectCard(id);
        return projectDao.deleteProject(id);
    }

    @Override
    public Project getProject(int id) {
        return projectDao.getProject(id);
    }

    @Override
    public List<Integer> getPageNumber(Date startDate, Date endDate) {
        List<Integer> pageNumbers = new ArrayList<>();
        int totalCount = projectDao.getProjectCount(startDate, endDate);
        int maxPage = totalCount / CARD_LIMIT;

        if (totalCount % CARD_LIMIT > 0) maxPage++;

        for (int i = 1; i <= maxPage; ++i) {
            pageNumbers.add(i);
        }

        return pageNumbers;
    }

    @Override
    public List<ProjectCard> getProjectCards(int pageNum, Date startDate, Date endDate) {
        int start = CARD_LIMIT * (pageNum-1);
        return projectCardDao.getProjectCards(start, CARD_LIMIT, startDate, endDate);
    }

    @Override
    public List<Project> getRecentProject(int start) {
        return projectDao.getRecentProjects(start, CARD_LIMIT);
    }

}



