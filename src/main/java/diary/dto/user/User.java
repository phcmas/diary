package diary.dto.user;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String password;
    private LocalDate createDate;
    private LocalDate modifyDate;
}
