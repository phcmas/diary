package diary.controller.projects;

import diary.dto.projects.Project;
import diary.param.ProjectParam;
import diary.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping(path="/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping(path="/lookup/{id}")
    public String showProject(@PathVariable(name = "id") int id, Model model) {
        Project project = projectService.getProject(id);
        model.addAttribute("project", project.toProjectParam());

        return "/projects/lookup";
    }

    @GetMapping("/registration")
    public String register() {
        return "/projects/registration";
    }

    @GetMapping(path="/modification/{id}")
    public String moveModificationPage(@PathVariable(name="id") int id, Model model) {
        Project project = projectService.getProject(id);
        model.addAttribute("project", project.toProjectParam());

        return "/projects/modification";
    }

    @GetMapping("/cards")
    public String projects() {
        return "/projects/cards";
    }

}
