package diary.dto.user;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String password;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
