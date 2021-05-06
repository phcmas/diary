package diary.dto.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ProjectRole {
    LEADER("PRINCIPAL"),
    SENIOR("SENIOR"),
    JUNIOR("JUNIOR");

    private final String projectRole;

    ProjectRole(String projectRole) {
        this.projectRole = projectRole;
    }

    public String getString() { return projectRole;}

}
