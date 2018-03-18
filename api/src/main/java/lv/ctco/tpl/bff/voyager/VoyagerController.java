package lv.ctco.tpl.bff.voyager;

import org.apache.commons.lang.text.StrSubstitutor;
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
public class VoyagerController {

    @Value("${graphql.endpoint:/graphql}")
    private String graphqlEndpoint;

    @Value("${voyager.pageTitle:Voyager}")
    private String pageTitle;

    @RequestMapping(value = "${voyager.mapping:/voyager}")
    public void voyager(HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        InputStream inputStream = new ClassPathResource("voyager.html").getInputStream();
        String template = StreamUtils.copyToString(inputStream, defaultCharset());

        Map<String, String> replacements = new HashMap<>();
        replacements.put("graphqlEndpoint", graphqlEndpoint);
        replacements.put("pageTitle", pageTitle);

        response.getOutputStream().write(StrSubstitutor.replace(template, replacements).getBytes(defaultCharset()));
    }
}
