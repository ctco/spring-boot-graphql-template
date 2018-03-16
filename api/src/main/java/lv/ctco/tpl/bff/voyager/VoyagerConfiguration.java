package lv.ctco.tpl.bff.voyager;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass(DispatcherServlet.class)
public class VoyagerConfiguration {

    @Bean
    @ConditionalOnProperty(value = "voyager.enabled", havingValue = "true", matchIfMissing = true)
    VoyagerController voyagerController() {
        return new VoyagerController();
    }
}
