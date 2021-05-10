package diary.dto.algorithm;

import diary.dto.enums.AlgorithmType;
import diary.dto.enums.Difficulty;
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
    private LocalDateTime solvedDate;
}
