package diary.dto.enums;

public enum Language {
    JAVA(0),
    C(1),
    CPP(2),
    JAVA_SCRIPT(3);

    private final int language;

    Language(int language) { this.language = language;}

    public String getString() {
        switch (language) {
            case 0: return "JAVA";
            case 1: return "C";
            case 2: return "CPP";
            case 3: return "JAVA_SCRIPT";
            default: return "ERROR";
        }
    }
}
