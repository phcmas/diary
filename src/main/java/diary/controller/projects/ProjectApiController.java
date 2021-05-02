package diary.controller.projects;

import diary.dto.projects.Project;
import diary.param.ProjectParam;
import diary.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/projects")
public class ProjectApiController {
    @Autowired
    ProjectService projectService;

    @PostMapping(path="/")
    public int addProject(@RequestBody ProjectParam param) {
        Project project = param.toProject();
        return projectService.addProject(project);
    }

    @PutMapping(path="/{id}")
    public int updateProject(@RequestBody ProjectParam param) {
        Project project = param.toProject();
        return projectService.updateProject(project);
    }

}
