package diary.dto.projects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCard {
    private int id;
    private int projectId;
    private String projectType;
    private String shortTitle;
    private String shortContent;
    private int memberCount;
    private LocalDateTime startDate;

    public ProjectCard(int projectId) {
        this.projectId = projectId;
    }
}
