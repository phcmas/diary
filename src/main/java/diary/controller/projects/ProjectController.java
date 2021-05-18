package diary.controller.projects;

import diary.dto.projects.Project;
import diary.dto.projects.ProjectCard;
import diary.dto.projects.ProjectMember;
import diary.param.ProjectCardParam;
import diary.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping(path="/lookup/{id}")
    public String showProject(@PathVariable(name = "id") int id, Model model) {
        Project project = projectService.get(id);
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
        model.addAttribute("project", project.toProjectParam());

        return "/projects/modification";
    }

    @GetMapping("/cards")
    public String showCards(@RequestParam(name = "date", required = false, defaultValue = "2021-01-01")
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                            @RequestParam(name = "start", required = false, defaultValue = "1") int pageNum,
                            Model model) {
        List<ProjectCard> cards = projectService.getCards(pageNum, date, date.plusYears(1));
        List<ProjectCardParam> params = new ArrayList<>();

        for (ProjectCard card : cards) {
            params.add(card.toProjectCardParam());
        }

        model.addAttribute("projectCards", params);
        model.addAttribute("date", date);
        return "/projects/cards";
    }
}

