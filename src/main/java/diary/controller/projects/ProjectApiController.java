package diary.controller.projects;

import diary.dto.projects.Project;
import diary.param.ProjectParam;
import diary.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/projects")
public class ProjectApiController {
    @Autowired
    ProjectService projectService;

    @PostMapping(path="/")
    public int addProject(@RequestBody ProjectParam param) throws ParseException {
        Project project = param.toProject();
        // members에 관한 처리는 이후 구현계획
        List<String> members = new ArrayList<>();
        members.add("temp");
        return projectService.addProject(project, members);
    }

    @PutMapping(path="/{id}")
    public int updateProject(@RequestBody ProjectParam param) throws ParseException {
        Project project = param.toProject();
        return projectService.updateProject(project);
    }


    @DeleteMapping(path="/{id}")
    public int deleteProject(@PathVariable(name="id", required = true) int id) {
        return projectService.deleteProject(id);
    }
}
