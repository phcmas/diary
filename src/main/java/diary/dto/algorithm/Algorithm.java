package diary.dto.algorithm;

import diary.dto.enums.AlgorithmType;
import diary.dto.enums.Difficulty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Algorithm {
    private int id;
    private String title;
    private LocalDateTime solvedDate;
    private String source;
    private AlgorithmType type;
    private String language;
    private Difficulty difficulty;
    private String explanation;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private int fileId;
}
