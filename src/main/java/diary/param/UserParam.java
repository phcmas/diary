package diary.param;

import diary.dto.user.User;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UserParam {
    private String name;
    private String password;

    @Builder
    public UserParam(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User toUser() {
        return User.builder()
                .name(name).password(password)
                .createDate(LocalDate.now()).modifyDate(LocalDate.now()).build();
    }

}
