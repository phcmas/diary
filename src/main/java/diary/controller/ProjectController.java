package diary.controller;

import diary.dto.projects.Project;
import diary.param.ProjectParam;
import diary.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping(path="/post")
    public int registerProject(@RequestBody ProjectParam projectParam) {
        Project project = projectParam.toProject();
        return 1;
    }


}
