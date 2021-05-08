package diary.dto.enums;

public enum Difficulty {
    EASY(0),
    MEDIDUM(1),
    HARD(2);

    private final int difficulty;

    Difficulty(int difficulty) {this.difficulty = difficulty;}

    public String getString() {
        switch (difficulty) {
            case 0: return "EASY";
            case 1: return "MEDIUM";
            case 2: return "HARD";
            default: return "ERROR";
        }
    }

}
