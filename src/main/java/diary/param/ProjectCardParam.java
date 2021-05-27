package diary.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private LocalDate startDate;
    private LocalDate endDate;
}
