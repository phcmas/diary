package diary.dto.projects;

import diary.dto.enums.ProjectType;
import diary.param.ProjectCardParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCard {
    private int id;
    private int projectId;
    private ProjectType projectType;
    private String shortTitle;
    private String shortContent;
    private int memberCount;
    private LocalDate startDate;

    public ProjectCardParam toProjectCardParam() {
        return ProjectCardParam.builder()
                .id(id).projectId(projectId).projectType(projectType.getString())
                .shortTitle(shortTitle).shortContent(shortContent)
                .memberCount(memberCount).build();
    }
}
