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
    private int userId;
    private String userName;
    private int projectId;

    public ProjectMember(int userId, String userName, int projectId) {
        this.userId = userId;
        this.userName = userName;
        this.projectId = projectId;
    }
}
