package diary.dto.projects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCard {
    private int id;
    private int projectId;

    public ProjectCard(int projectId) {
        this.projectId = projectId;
    }
}
