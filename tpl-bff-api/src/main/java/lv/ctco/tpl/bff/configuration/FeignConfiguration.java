package lv.ctco.tpl.bff.configuration;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.slf4j.Slf4jLogger;
import lv.ctco.tpl.bff.integration.icndb.ICNDB;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public ICNDB provideICNDB(@Value("${application.icndb-url}") String baseUrl) {
        return baseBuilder()
            .target(ICNDB.class, baseUrl);
    }

    private Feign.Builder baseBuilder() {
        return Feign.builder()
            .decoder(new JacksonDecoder())
            .logger(new Slf4jLogger())
            .logLevel(Logger.Level.FULL);
    }
}
