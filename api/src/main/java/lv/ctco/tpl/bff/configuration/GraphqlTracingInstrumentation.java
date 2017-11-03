
package lv.ctco.tpl.bff.configuration;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import graphql.execution.instrumentation.tracing.TracingInstrumentation;
import graphql.execution.instrumentation.tracing.TracingSupport;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Component
public class GraphqlTracingInstrumentation extends TracingInstrumentation {

    private static final String DURATION = "duration";
    private final Logger log = Logger.getLogger(GraphqlTracingInstrumentation.class.getSimpleName());

    @Override
    public CompletableFuture<ExecutionResult> instrumentExecutionResult(ExecutionResult executionResult,
                                                                        InstrumentationExecutionParameters parameters) {
        TracingSupport tracingSupport = parameters.getInstrumentationState();
        Long executionTime = getTime(tracingSupport.snapshotTracingData());
        log.info(String.format("Query (%s) Execution time : %sms", parameters.getOperation(), executionTime));
        return super.instrumentExecutionResult(executionResult, parameters);
    }

    private Long getTime(Map<String, Object> tracingData) {
        if (tracingData != null && tracingData.containsKey(DURATION)) {
            return TimeUnit.MILLISECONDS.convert((long) tracingData.get(DURATION), TimeUnit.NANOSECONDS);
        }
        return null;
    }

}
