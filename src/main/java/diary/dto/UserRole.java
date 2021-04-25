package diary.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    private int id;
    private int userId;
    private String roleName;

    public UserRole (int userId, String roleName) {
        this.userId = userId;
        this.roleName = roleName;
    }
}
