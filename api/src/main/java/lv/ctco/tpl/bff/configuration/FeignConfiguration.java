package lv.ctco.tpl.bff.configuration;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import feign.Logger;
import feign.RequestLine;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;
import feign.jackson.JacksonDecoder;
import feign.slf4j.Slf4jLogger;
import lv.ctco.tpl.bff.connectors.icndb.ICNDB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    private static final int EXECUTION_TIMEOUT_IN_MILLIS = 2000;

    @Bean
    public ICNDB provideICNDB(AppProperties props) {
        return baseBuilder()
            .target(ICNDB.class, props.getIcndbUrl());
    }

    private HystrixFeign.Builder baseBuilder() {
        return HystrixFeign.builder()
            .setterFactory(setterFactory())
            .decoder(new JacksonDecoder())
            .logger(new Slf4jLogger())
            .logLevel(Logger.Level.FULL);
    }

    /**
     * Full list of hystrix properties is available <a href="https://github.com/Netflix/Hystrix/wiki/Configuration#CommandProperties">here</a>.
     * Default isolation strategy is set to THREAD
     */
    private SetterFactory setterFactory() {
        return (target, method) -> {
                String commandKey = method.getAnnotation(RequestLine.class).value();
                String groupKey = target.name();

                HystrixCommandProperties.Setter commandPropertiesDefaults = HystrixCommandProperties.Setter()
                    .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                    .withExecutionTimeoutInMilliseconds(EXECUTION_TIMEOUT_IN_MILLIS);

                return HystrixCommand.Setter
                    .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                    .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
                    .andCommandPropertiesDefaults(commandPropertiesDefaults);
            };
    }
}
