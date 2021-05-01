package diary.param;

import diary.dto.projects.Project;
import diary.utility.Utility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectParam {
    private String title;
    private Date startDate;
    private Date endDate;
    private String projectType;
    private String situation;
    private String content;
    private String testScenario;

    public Project toProject() {
        return Project.builder().title(title)
                .projectType(Utility.getProjectType(projectType))
                .situation(situation).content(content)
                .testScenario(testScenario).build();
    }

}
