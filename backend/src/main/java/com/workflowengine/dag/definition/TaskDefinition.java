package com.workflowengine.dag.definition;

import lombok.Data;
import java.util.List;

@Data
public class TaskDefinition {
    private String taskId;
    private String type;       // e.g. "SHELL" or "HTTP"
    private String command;    // used when type = SHELL
    private String url;        // used when type = HTTP
    private List<String> dependsOn;
}
