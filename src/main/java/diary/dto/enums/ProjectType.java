package diary.dto.enums;

public enum ProjectType {
    FUNCTION_DEVELOPMENT(0),
    PERFORMANCE_IMPROVEMENT(1),
    ERROR_RESOLUTION(2);

    private final int projectType;

    ProjectType(int projectType) {
        this.projectType = projectType;
    }

    public String getString() {
        switch (projectType) {
            case 0:
                return "기능개발";
            case 1:
                return "성능개선";
            case 2:
                return "오류해결";
            default:
                return "??";
        }
    }
}
