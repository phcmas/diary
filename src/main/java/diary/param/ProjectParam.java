package diary.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import diary.dto.projects.Project;
import diary.utility.Utility;
import lombok.*;

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
    private Date startDate;
    private Date endDate;
    private String projectType;
    private String situation;
    private String content;
    private String testScenario;

    private String startDateFormat;
    private String endDateFormat;

    public Project toProject() {
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
