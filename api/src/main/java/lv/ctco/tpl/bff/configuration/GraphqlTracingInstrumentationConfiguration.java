
package lv.ctco.tpl.bff.configuration;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import graphql.execution.instrumentation.tracing.TracingInstrumentation;
import graphql.execution.instrumentation.tracing.TracingSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j(topic = "graphql")
public class GraphqlTracingInstrumentationConfiguration extends TracingInstrumentation {

    private static final String DURATION = "duration";

    @Override
    public CompletableFuture<ExecutionResult> instrumentExecutionResult(ExecutionResult executionResult,
                                                                        InstrumentationExecutionParameters parameters) {
        if (log.isDebugEnabled()) {
            logTracingResults(parameters);
        }
        return super.instrumentExecutionResult(executionResult, parameters);
    }

    private void logTracingResults(InstrumentationExecutionParameters parameters) {
        TracingSupport tracingSupport = parameters.getInstrumentationState();
        Long executionTime = getTime(tracingSupport.snapshotTracingData());
        if (executionTime != null) {
            log.debug(String.format("Query (%s) Execution time : %s ms", parameters.getOperation(), executionTime));
        }
    }

    private Long getTime(Map<String, Object> tracingData) {
        if (tracingData != null && tracingData.containsKey(DURATION)) {
            return TimeUnit.MILLISECONDS.convert((long) tracingData.get(DURATION), TimeUnit.NANOSECONDS);
        }
        return null;
    }

}
