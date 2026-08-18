package com.workflowengine.dag.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "task_definitions")
@Data
public class TaskDefinitionEntity {

    @Id
    @GeneratedValue
    private UUID id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dag_definition_id", nullable = false)
    private DagDefinitionEntity dagDefinition;
   
    @Column(name = "task_id", nullable = false)
    private String taskId;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "command")
    private String command;

    @Column(name = "url")
    private String url;

    @Column(name = "depends_on")
    private List<String> dependsOn;
}
