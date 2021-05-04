package diary.service;

import diary.dto.projects.Project;
import diary.dto.projects.ProjectCard;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ProjectService {
    public int addProject(Project project, List<String> members);
    public int updateProject(Project project);
    public int deleteProject(int id);
    public Project getProject(int id);
    public List<ProjectCard> getProjectCards(int start, Date startDate, Date endDate);
    public List<Project> getRecentProject(int start);
    public ProjectCard makeProjectCard(Project project, int projectId, int memberCount);
}
