package diary.dto.algorithm;

import diary.dto.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {
    private int id;
    private String fileName;
    private Language language;
    private int algorithmId;
}
