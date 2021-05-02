package diary.dto.enums;

public enum ProjectType {
    FunctionDevelopment("FunctionDevelopment"),
    PerformanceImprovement("PerformanceImprovement"),
    ErrorResolution("ErrorResolution");

    private final String projectType;

    ProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getString() {
        return projectType;
    }

    public String getStringFormat() {
        switch (projectType) {
            case "FunctionDevelopment":
                return "기능개발";
            case "PerformanceImprovement":
                return "성능개선";
            case "ErrorResolution":
                return "오류해결";
            default:
                return "??";
        }
    }
}
