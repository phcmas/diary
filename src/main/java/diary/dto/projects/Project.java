package diary.dto.projects;

import diary.dto.enums.ProjectType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private int id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private ProjectType projectType;
    private String situation;
    private String content;
    private String testScenario;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
