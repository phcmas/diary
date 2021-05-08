package diary.dto.projects;


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
    private String name;
    private int projectId;

    public ProjectMember(String userName, int projectId) {
        this.name = userName;
        this.projectId = projectId;
    }
}
