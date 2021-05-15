package diary.param;

import diary.dto.algorithm.AlgorithmCard;
import diary.dto.enums.AlgorithmType;
import diary.dto.enums.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlgorithmCardParam {
    private int id;
    private int algorithmId;
    private String shortTitle;
    private String shortExplanation;
    private String type;
    private String difficulty;
    private LocalDate solvedDate;

    public AlgorithmCard toAlgorithmParam() {
        return AlgorithmCard.builder().id(id)
                .algorithmId(algorithmId).shortTitle(shortTitle)
                .shortExplanation(shortExplanation)
                .type(AlgorithmType.valueOf(type))
                .difficulty(Difficulty.valueOf(difficulty))
                .solvedDate(solvedDate).build();
    }
}
