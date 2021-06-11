package diary.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCardParam {
    private int id;
    private int projectId;
    private String projectType;
    private String shortTitle;
    private String shortContent;
    private int memberCount;
    private String startDate;
    private String endDate;
}
