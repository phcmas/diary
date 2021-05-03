package diary.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import diary.dto.projects.Project;
import diary.utility.Utility;
import lombok.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;

//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectParam {
    private int id;
    private String title;
    private String startDate;
    private String endDate;
    private String projectType;
    private String situation;
    private String content;
    private String testScenario;

    public Project toProject() throws ParseException {
        Project newProject = Project.builder().title(title)
                .startDate(Utility.convert(startDate))
                .endDate(Utility.convert(endDate))
                .projectType(Utility.getProjectType(projectType))
                .situation(situation).content(content)
                .testScenario(testScenario)
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now()).build();

        if (id != -1) newProject.setId(id);
        return newProject;
    }

}
