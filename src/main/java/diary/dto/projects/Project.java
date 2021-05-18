package diary.dto.projects;

import diary.dto.enums.ProjectType;
import diary.param.ProjectParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private int id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectType projectType;
    private String situation;
    private String content;
    private String testScenario;
    private LocalDate createDate;
    private LocalDate modifyDate;

    public ProjectParam toProjectParam() {
        return ProjectParam.builder()
                .id(id).title(title)
                .startDate(startDate).endDate(endDate)
                .projectType(projectType.getString())
                .situation(situation)
                .content(content)
                .testScenario(testScenario)
                .build();
    }
}

