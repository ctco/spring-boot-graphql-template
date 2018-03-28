package lv.ctco.tpl.bff.playground;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass(DispatcherServlet.class)
public class PlaygroundConfiguration {

    @Bean
    @ConditionalOnProperty(value = "playground.enabled", havingValue = "true", matchIfMissing = true)
    PlaygroundController playgroundController() {
        return new PlaygroundController();
    }
}
