package diary.dto.algorithm;

import com.fasterxml.jackson.annotation.JsonFormat;
import diary.dto.enums.AlgorithmType;
import diary.dto.enums.Difficulty;
import diary.param.AlgorithmCardParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlgorithmCard {
    private int id;
    private int algorithmId;
    private String shortTitle;
    private String shortExplanation;
    private AlgorithmType type;
    private Difficulty difficulty;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate solvedDate;

    public AlgorithmCardParam toAlgorithmCardParam() {
        return AlgorithmCardParam.builder()
                .id(id).algorithmId(algorithmId)
                .shortTitle(shortTitle)
                .shortExplanation(shortExplanation)
                .type(type.getString())
                .difficulty(difficulty.getString())
                .solvedDate(solvedDate).build();
    }
}
