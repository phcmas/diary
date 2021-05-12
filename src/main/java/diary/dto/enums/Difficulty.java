package diary.dto.enums;

public enum Difficulty {
    EASY(0),
    MEDIUM(1),
    HARD(2);

    private final int difficulty;

    Difficulty(int difficulty) {this.difficulty = difficulty;}

    public String getString() {
        switch (difficulty) {
            case 0: return "Easy";
            case 1: return "Medium";
            case 2: return "Hard";
            default: return "ERROR";
        }
    }

}
