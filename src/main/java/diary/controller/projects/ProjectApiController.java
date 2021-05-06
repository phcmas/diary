package diary.controller.projects;

import diary.dto.projects.MemberInfo;
import diary.dto.projects.Project;
import diary.param.ProjectParam;
import diary.service.ProjectService;
import diary.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path="/projects")
public class ProjectApiController {
    @Autowired
    ProjectService projectService;

    @PostMapping(path="/")
    public int addProject(@RequestBody ProjectParam param) throws ParseException {
        Project project = param.toProject();
        List<String> members = param.getMemberInfo();

        return projectService.addProject(project, members);
    }

    @PutMapping(path="/{id}")
    public int updateProject(@RequestBody ProjectParam param) throws ParseException {
        Project project = param.toProject();
        List<String> members = param.getMemberInfo();

        return projectService.updateProject(project, members);
    }


    @DeleteMapping(path="/{id}")
    public int deleteProject(@PathVariable(name="id", required = true) int id) {
        return projectService.deleteProject(id);
    }

    @GetMapping(path="/pagenum")
    public List<Integer> test(@RequestParam(name="year") String year) throws ParseException {
        Date startDate = Utility.yearToDate(year);
        Date endDate = Utility.addTime(startDate, 1, 0,0);

        return projectService.getPageNumber(startDate, endDate);
    }
}
