package diary.service.impl;

import diary.dao.projects.ProjectCardDao;
import diary.dao.projects.ProjectDao;
import diary.dao.projects.ProjectMemberDao;
import diary.dao.user.UserDao;
import diary.dto.projects.Project;
import diary.dto.projects.ProjectCard;
import diary.dto.projects.ProjectMember;
import diary.service.ProjectService;
import diary.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
                .projectId(projectId).projectType(project.getProjectType())
                .memberCount(memberCount).startDate(project.getStartDate())
                .endDate(project.getEndDate()).build();

        String shortTitle = Utility.cutString(project.getTitle(), 10);
        String shortContent = Utility.cutString(project.getContent(), 50);
        projectCard.setShortTitle(shortTitle);
        projectCard.setShortContent(shortContent);

        return projectCard;
    }

    @Override
    @Transactional
    public int add(Project project, List<String> names) {
        int projectId = projectDao.add(project);

        for (String name : names) {
            projectMemberDao.add(name, projectId);
        }

        ProjectCard projectCard = makeProjectCard(project, projectId, names.size());
        projectCardDao.add(projectCard);

        return projectId;
    }

    @Override
    @Transactional
    public int update(Project project, List<String> names) {
        int projectId = project.getId();
        int projectCardId = projectCardDao.getByProjectId(projectId).getId();

        ProjectCard projectCard = makeProjectCard(project, projectId, names.size());
        projectCard.setId(projectCardId);
        projectCardDao.update(projectCard);

        projectMemberDao.deleteByProjectId(projectId);
        projectMemberDao.addBatch(names, projectId);

        return projectDao.update(project);
    }

    @Override
    @Transactional
    public int delete(int id) {
        projectMemberDao.deleteByProjectId(id);
        projectCardDao.deleteByProjectId(id);
        return projectDao.delete(id);
    }

    @Override
    public Project get(int id) {
        return projectDao.get(id);
    }

    @Override
    public List<Integer> getPageNumbers(LocalDate fromDate, LocalDate toDate) {
        int count = projectDao.getCount(fromDate, toDate);
        return Utility.getPageList(count, CARD_LIMIT);
    }

    @Override
    public List<ProjectMember> getMembers(int projectId) {
        return projectMemberDao.getByProjectId(projectId);
    }

    @Override
    public List<String> getMemberNames(int projectId) {
        List<ProjectMember> members = projectMemberDao.getByProjectId(projectId);
        List<String> names = new ArrayList<>();

        for (ProjectMember member : members) {
            names.add(member.getName());
        }

        return names;
    }

    @Override
    public List<ProjectCard> getCards(int pageNum, LocalDate fromDate, LocalDate toDate) {
        int start = CARD_LIMIT * (pageNum-1);
        return projectCardDao.getList(start, CARD_LIMIT, fromDate, toDate);
    }

    @Override
    public List<Project> getRecentProject(int start) {
        return projectDao.getRecentList(start, CARD_LIMIT);
    }

}



