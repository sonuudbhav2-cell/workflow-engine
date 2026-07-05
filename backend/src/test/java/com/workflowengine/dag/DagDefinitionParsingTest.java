package com.workflowengine.dag;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workflowengine.dag.definition.DagDefinition;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class DagDefinitionParsingTest {

    @Test
    void parsesSampleJsonIntoDagDefinition() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();

        InputStream jsonStream = getClass()
                .getClassLoader()
                .getResourceAsStream("dags/daily-sales-etl.json");

        DagDefinition dag = jsonMapper.readValue(jsonStream, DagDefinition.class);

        System.out.println("Parsed DAG: " + dag);

        assertEquals("daily-sales-etl", dag.getDagId());
        assertEquals(4, dag.getTasks().size());
        assertEquals("extract", dag.getTasks().get(0).getTaskId());
        assertTrue(dag.getTasks().get(1).getDependsOn().contains("extract"));
    }
}
