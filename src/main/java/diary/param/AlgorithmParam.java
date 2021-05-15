package diary.param;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import diary.dto.algorithm.Algorithm;
import diary.dto.enums.AlgorithmType;
import diary.dto.enums.Difficulty;
import diary.dto.enums.Language;
import diary.utility.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlgorithmParam {
    private int id;
    private String title;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate solvedDate;

    private String source;
    private String type;
    private String language;
    private String difficulty;
    private String explanation;
    private String content;

    public Algorithm toAlgorithm() throws ParseException {
        return Algorithm.builder().id(id)
                .title(title).solvedDate(solvedDate)
                .source(source).type(AlgorithmType.valueOf(type))
                .language(Language.valueOf(language)).difficulty(Difficulty.valueOf(difficulty))
                .explanation(explanation).content(content)
                .createDate(LocalDate.now())
                .modifyDate(LocalDate.now())
                .build();
    }
}
