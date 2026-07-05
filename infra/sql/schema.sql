CREATE TABLE dag_definitions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    dag_id VARCHAR(255) NOT NULL UNIQUE,
    schedule VARCHAR(100),
    definition_json JSONB NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE task_definitions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    dag_definition_id UUID NOT NULL REFERENCES dag_definitions(id) ON DELETE CASCADE,
    task_id VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    command TEXT,
    url TEXT,
    depends_on TEXT[],
    UNIQUE (dag_definition_id, task_id)
);

CREATE TABLE dag_runs (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    dag_definition_id UUID NOT NULL REFERENCES dag_definitions(id),
    status VARCHAR(50) NOT NULL,
    trigger_type VARCHAR(50) NOT NULL,
    idempotency_key VARCHAR(255) NOT NULL UNIQUE,
    started_at TIMESTAMP,
    finished_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE task_instances (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    dag_run_id UUID NOT NULL REFERENCES dag_runs(id) ON DELETE CASCADE,
    task_definition_id UUID NOT NULL REFERENCES task_definitions(id),
    status VARCHAR(50) NOT NULL,
    retry_count INT NOT NULL DEFAULT 0,
    max_retries INT NOT NULL DEFAULT 3,
    started_at TIMESTAMP,
    finished_at TIMESTAMP,
    error_message TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);
