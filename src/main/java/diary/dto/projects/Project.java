package diary.dto.projects;

import diary.dto.enums.ProjectType;
import diary.param.ProjectParam;
import diary.utility.Utility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private int id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ProjectType projectType;
    private String content;
    private String testScenario;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public ProjectParam toProjectParam(List<ProjectMember> members) {
        ProjectParam param = ProjectParam.builder()
                .id(id).title(title)
                .startDate(Utility.convert(startDate))
                .endDate(Utility.convert(endDate))
                .projectType(projectType.getDisplayFormat())
                .content(content)
                .testScenario(testScenario)
                .build();

        List<String> memberNames = new ArrayList<>();
        for (ProjectMember member : members) {
            memberNames.add(member.getUserName());
        }

        return param;
    }
}

