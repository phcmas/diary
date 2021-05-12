package diary.dto.algorithm;

import diary.dto.enums.AlgorithmType;
import diary.dto.enums.Difficulty;
import diary.param.AlgorithmParam;
import diary.utility.Utility;
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

    public AlgorithmParam toAlgorithmParam() {
        return AlgorithmParam.builder()
                .id(id).title(title)
                .solvedDate(Utility.convert(solvedDate))
                .source(source).type(type.getString())
                .language(language).difficulty(difficulty.getString())
                .explanation(explanation).content(content)
                .build();
    }
}
