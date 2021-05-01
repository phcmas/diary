package diary.dto.enums;

// spring security의 특성상 각 enum의 string 값은 반드시 ROLE_*** 형태로 만들어져야함.
public enum UserAuthority {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    GUEST("ROLE_GUEST");

    private final String userAuthority;

    UserAuthority(String userAuthority) {
        this.userAuthority = userAuthority;
    }

    public String getString() {return userAuthority;}
}
