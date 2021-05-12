package diary.controller.projects;

import diary.dto.projects.Project;
import diary.dto.projects.ProjectCard;
import diary.dto.projects.ProjectMember;
import diary.service.ProjectService;
import diary.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path="/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping(path="/lookup/{id}")
    public String showProject(@PathVariable(name = "id") int id, Model model) {
        Project project = projectService.get(id);
        List<ProjectMember> members = projectService.getProjectMembers(id);
        model.addAttribute("project", project.toProjectParam());

        return "/projects/lookup";
    }

    @GetMapping("/registration")
    public String addProject() {
        return "/projects/registration";
    }

    @GetMapping(path="/modification/{id}")
    public String modifyProject(@PathVariable(name="id") int id, Model model) {
        Project project = projectService.get(id);
        List<ProjectMember> members = projectService.getProjectMembers(id);
        model.addAttribute("project", project.toProjectParam());

        return "/projects/modification";
    }

    @GetMapping("/cards")
    public String showCards(@RequestParam(name = "year", required = false, defaultValue = "2021") String year,
                            @RequestParam(name = "start", required = false, defaultValue = "1") int pageNum,
                            Model model) throws ParseException {
        Date startDate = Utility.yearToDate(year);
        Date endDate = Utility.addTime(startDate,1,0,0);
        List<ProjectCard> projects = projectService.getProjectCards(pageNum, startDate, endDate);

        model.addAttribute("projectCards", projects);
        model.addAttribute("year", year);
        return "/projects/cards";
    }
}

