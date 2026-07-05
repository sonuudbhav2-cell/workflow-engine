package com.workflowengine.dag.definition;

import lombok.Data;
import java.util.List;

@Data
public class DagDefinition {
    private String dagId;
    private String schedule;   // cron expression
    private List<TaskDefinition> tasks;
}
