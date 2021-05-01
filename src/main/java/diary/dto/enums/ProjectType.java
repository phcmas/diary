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
}
