package diary.dto.algorithm;

import diary.dto.enums.AlgorithmType;
import diary.dto.enums.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Algorithm {
    private int id;
    private String title;
    private LocalDateTime solvedDate;
    private String language;
    private AlgorithmType type;
    private String source;
    private Difficulty difficulty;
    private String explanation;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private int fileId;
}
