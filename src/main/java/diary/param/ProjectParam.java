package diary.param;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import diary.dto.enums.ProjectType;
import diary.dto.projects.Project;
import diary.utility.LocalDateDeserializer;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectParam {
    private int id;
    private String title;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;

    private String projectType;
    private String situation;
    private String content;
    private String testScenario;
    private List<String> names;

    public Project toProject() throws ParseException {
        Project newProject = Project.builder().title(title)
                .startDate(startDate).endDate(endDate)
                .projectType(ProjectType.valueOf(projectType))
                .situation(situation).content(content)
                .testScenario(testScenario)
                .createDate(LocalDate.now())
                .modifyDate(LocalDate.now()).build();

        if (id != -1) newProject.setId(id);
        return newProject;
    }

    public List<String> getMemberNames() {
        return names;
    }

}
