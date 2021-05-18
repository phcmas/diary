package diary.dto.algorithm;

import diary.dto.enums.AlgorithmType;
import diary.dto.enums.Difficulty;
import diary.dto.enums.Language;
import diary.param.AlgorithmParam;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Algorithm {
    private int id;
    private String title;
    private LocalDate solvedDate;
    private String source;
    private AlgorithmType type;
    private Language language;
    private Difficulty difficulty;
    private String explanation;
    private String content;
    private LocalDate createDate;
    private LocalDate modifyDate;
    private int fileId;

    public AlgorithmParam toAlgorithmParam(FileInfo fileInfo) {
        int fileId = fileInfo.getId();
        String fileName = fileInfo.getFileName();

        return AlgorithmParam.builder()
                .id(id).fileId(fileId).fileName(fileName).title(title)
                .solvedDate(solvedDate)
                .source(source).type(type.getString())
                .language(language.getString()).difficulty(difficulty.getString())
                .explanation(explanation).content(content)
                .build();
    }
}
