package diary.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    private String solvedDate;
}
