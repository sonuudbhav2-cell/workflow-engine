package com.workflowengine.dag.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "dag_definitions")
@Data
public class DagDefinitionEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "dag_id", nullable = false, unique = true)
    private String dagId;

    @Column(name = "schedule")
    private String schedule;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "definition_json", nullable = false, columnDefinition = "jsonb")
    private String definitionJson;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}