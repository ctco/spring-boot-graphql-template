
package lv.ctco.tpl.bff.configuration;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import graphql.execution.instrumentation.tracing.TracingInstrumentation;
import graphql.execution.instrumentation.tracing.TracingSupport;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Component
public class GraphqlTracingInstrumentation extends TracingInstrumentation {

    private static final Logger logger = Logger.getLogger(GraphqlTracingInstrumentation.class.getSimpleName());

    @Override
    public CompletableFuture<ExecutionResult> instrumentExecutionResult(ExecutionResult executionResult, InstrumentationExecutionParameters parameters) {
        TracingSupport tracingSupport = parameters.getInstrumentationState();
        String operationsName = getOperationsName(parameters);
        logger.info(String.format("Query (%s) Execution time : %sms", operationsName, getTime(tracingSupport.snapshotTracingData())));
        logger.info(String.format("Query (%s) : %s", operationsName, parameters.getQuery()));
        logger.info(String.format("Query (%s) Variables : %s", operationsName, parameters.getVariables()));
        return super.instrumentExecutionResult(executionResult, parameters);
    }

    private String getOperationsName(InstrumentationExecutionParameters parameters) {
        return parameters.getOperation() != null ? parameters.getOperation() : UUID.randomUUID().toString();
    }

    private Long getTime(Map<String, Object> tracingData) {
        if (tracingData != null && tracingData.containsKey("duration")) {
            return TimeUnit.MILLISECONDS.convert((long) tracingData.get("duration"), TimeUnit.NANOSECONDS);
        }
        return null;
    }

}
