package diary.service;

import diary.dto.projects.Project;

import java.util.List;

public interface ProjectService {
    public int addProject(Project project);
    public int updateProject(Project project);
    public Project getProject(int id);
    public List<Project> getRecentProject(int start);
}
