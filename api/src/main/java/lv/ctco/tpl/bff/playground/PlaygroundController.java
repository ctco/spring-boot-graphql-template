package lv.ctco.tpl.bff.playground;

import org.apache.commons.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.Charset.defaultCharset;

@Controller
public class PlaygroundController {

    @Value("${playground.endpoint:/graphql}")
    private String graphqlEndpoint;

    @Value("${playground.pageTitle:Playground}")
    private String pageTitle;

    @RequestMapping(value = "${playground.mapping:/playground}")
    public void voyager(HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        InputStream inputStream = new ClassPathResource("playground.html").getInputStream();
        String template = StreamUtils.copyToString(inputStream, defaultCharset());

        Map<String, String> replacements = new HashMap<>();
        replacements.put("graphqlEndpoint", graphqlEndpoint);
        replacements.put("pageTitle", pageTitle);

        response.getOutputStream().write(StrSubstitutor.replace(template, replacements).getBytes(defaultCharset()));
    }
}
