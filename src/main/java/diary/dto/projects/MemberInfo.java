package diary.dto.projects;

import diary.dto.enums.ProjectRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MemberInfo {
    private String userName;
    private ProjectRole role;
}
