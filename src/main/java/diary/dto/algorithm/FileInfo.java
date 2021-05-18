package diary.dto.algorithm;

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
    private int algorithmId;
    private String fileName;
    private String saveFileName;
    private String contentType;
}
