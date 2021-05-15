package diary.controller.projects;

import diary.dto.projects.Project;
import diary.dto.projects.ProjectMember;
import diary.param.ProjectParam;
import diary.service.ProjectService;
import diary.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/projects")
public class ProjectApiController {
    @Autowired
    ProjectService projectService;

    @PostMapping(path="/")
    public int addProject(@RequestBody ProjectParam param) throws ParseException {
        Project project = param.toProject();
        List<String> names = param.getMemberNames();

        return projectService.add(project, names);
    }

    @PutMapping(path="/{id}")
    public int updateProject(@RequestBody ProjectParam param) throws ParseException {
        Project project = param.toProject();
        List<String> names = param.getMemberNames();

        return projectService.update(project, names);
    }

    @DeleteMapping(path="/{id}")
    public int deleteProject(@PathVariable(name="id", required = true) int id) {
        return projectService.delete(id);
    }

    @GetMapping(path="/pagenum")
    public List<Integer> getPageNumbers(@RequestParam(name="date", required = true)
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return projectService.getPageNumbers(date, date.plusYears(1));
    }

    @GetMapping(path="/names")
    public List<String> getMemberNames(@RequestParam(name="id") int id) {
        List<ProjectMember> projectMembers = projectService.getMembers(id);
        return Utility.getNames(projectMembers);
    }

}
