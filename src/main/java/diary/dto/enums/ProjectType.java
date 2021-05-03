package diary.dto.enums;

public enum ProjectType {
    FUNCTION_DEVELOPMENT("FUNCTION_DEVELOPMENT"),
    PERFORMANCE_IMPROVEMENT("PERFORMANCE_IMPROVEMENT"),
    ERROR_RESOLUTION("ERROR_RESOLUTION");

    private final String projectType;

    ProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getString() {
        return projectType;
    }

    public String getStringFormat() {
        switch (projectType) {
            case "FUNCTION_DEVELOPMENT":
                return "기능개발";
            case "PERFORMANCE_IMPROVEMENT":
                return "성능개선";
            case "ERROR_RESOLUTION":
                return "오류해결";
            default:
                return "??";
        }
    }
}
