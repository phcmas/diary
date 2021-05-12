package diary.param;


import diary.dto.algorithm.Algorithm;
import diary.dto.enums.AlgorithmType;
import diary.dto.enums.Difficulty;
import diary.utility.Utility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlgorithmParam {
    private int id;
    private String title;
    private String solvedDate;
    private String source;
    private String type;
    private String language;
    private String difficulty;
    private String explanation;
    private String content;

    public Algorithm toAlgorithm() throws ParseException {
        return Algorithm.builder().id(id)
                .title(title).solvedDate(Utility.convert(solvedDate))
                .source(source).type(AlgorithmType.valueOf(type))
                .language(language).difficulty(Difficulty.valueOf(difficulty))
                .explanation(explanation).content(content)
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .build();
    }
}
