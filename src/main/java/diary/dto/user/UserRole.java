package diary.dto.user;

import diary.dto.enums.UserAuthority;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    private int id;
    private int userId;
    private String name;
    private UserAuthority roleName;

    public UserRole (int userId, UserAuthority roleName) {
        this.userId = userId;
        this.roleName = roleName;
    }
}
