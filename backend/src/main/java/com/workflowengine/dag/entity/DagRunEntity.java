package com.workflowengine.dag.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "dag_runs")
@Data
public class DagRunEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dag_definition_id", nullable = false)
    private DagDefinitionEntity dagDefinition;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "trigger_type", nullable = false)
    private String triggerType;

    @Column(name = "idempotency_key", nullable = false, unique = true)
    private String idempotencyKey;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "finished_at")
    private LocalDateTime finishedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
