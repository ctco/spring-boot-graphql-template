package lv.ctco.tpl.bff.configuration;

import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@ConfigurationProperties(prefix="app")
@Component
@Data
public class AppProperties {

    @NotNull @URL
    private String icndbUrl;
}
