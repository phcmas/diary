package diary.dto.algorithm;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileInfo {
    private int id;
    private String fileName;
    private String language;
    private int algorithmId;
}
