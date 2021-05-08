package diary.dto.algorithm;

import diary.dto.enums.AlgorithmType;
import diary.dto.enums.Difficulty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class Algorithm {
    private int id;
    private String title;
    private LocalDateTime solvedDate;
    private String language;
    private AlgorithmType type;
    private String source;
    private Difficulty difficulty;
    private String explanation;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private int fileId;
}
