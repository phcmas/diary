package diary.dto.enums;

public enum UserAuthority {
    ADMIN(0),
    USER(1),
    GUEST(2);

    private final int userAuthority;

    UserAuthority(int userAuthority) {
        this.userAuthority = userAuthority;
    }

    // spring security의 특성상 각 enum의 string 값은 반드시 ROLE_*** 형태로 만들어져야함.
    public String getString() {
        switch (userAuthority) {
            case 0 : return "ROLE_ADMIN";
            case 1 : return "ROLE_USER";
            case 2 : return "ROLE_GUEST";
            default : return "ERROR";
        }
    }
}
