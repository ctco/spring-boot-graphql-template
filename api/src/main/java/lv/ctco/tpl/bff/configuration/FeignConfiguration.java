package lv.ctco.tpl.bff.configuration;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.slf4j.Slf4jLogger;
import lv.ctco.tpl.bff.connectors.icndb.ICNDB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public ICNDB provideICNDB(AppProperties props) {
        return baseBuilder()
            .target(ICNDB.class, props.getIcndbUrl());
    }

    private Feign.Builder baseBuilder() {
        return Feign.builder()
            .decoder(new JacksonDecoder())
            .logger(new Slf4jLogger())
            .logLevel(Logger.Level.FULL);
    }
}
