package diary.dto.projects;


import diary.dto.enums.ProjectRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMember {
    private int id;
    private String userName;
    private int projectId;

    public ProjectMember(String userName, int projectId) {
        this.userName = userName;
        this.projectId = projectId;
    }
}
