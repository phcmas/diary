package diary.dto.algorithm;

import com.fasterxml.jackson.annotation.JsonFormat;
import diary.dto.enums.AlgorithmType;
import diary.dto.enums.Difficulty;
import diary.param.AlgorithmCardParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    // JsonFormat에서 나중에 더 알아보자
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime solvedDate;

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
